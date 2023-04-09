#!/bin/sh
#create on 20230308

#check line sum match trailer number
function sumMatchCheck() {
  fileName=$1
  trailerNum=`zcat $filePath/$fileName|tail -1|awk -F "|" '{print$2}'`
  echo "sum in trailer: $trailerNum" >> $outputFile
  fileLineNum=`zcat $filePath/$fileName | wc -l`
  echo "sum by wc: $fileLineNum" >> $outputFile
  fileLineNum=`expr $fileLineNum-2`
  if (($fileLineNum==$trailerNum));then
    echo "sum not match: No" >> $outputFile
  else
    echo "sum not match: Yes -- this may be exception" >> $outputFile
  fi
}

#check new column exist
function newColumnCheck() {
  fileName=$1
  newColumnNameFeature=$2
  val_batch="val_batch"
  trade_summary="trade_summary"
  if [[ $fileName =~ $val_batch ]] || [[ $fileName =~ $trade_summary ]]; then
    if [ $newColumnNameFeature ];then
      # -c only get match num ; -i ignore case
      newClmExist=`zcat $filePath/$fileName|head -1|grep -c -i $newColumnNameFeature`
      if (($newClmExist>0));then
        echo "$newClmExist column not exist: No" >> $outputFile
      else
        echo "$newColumnNameFeature column not exist: Yes" >> $outputFile
      fi
    fi
  fi
}

#check duplicate column
function checkDuplicateColumn() {
  fileName=$1
  duplicateClm=""
  headLine=`zcat $filePath/$fileName|head -1`
  #split by | char
  headArray=(${headLine//\|/ })
  for compareSource in "${headArray[@]}"; do
    duplicateNum=0
    for compareDes in "${headArray[@]}"; do
      if [[ $compareSource == $compareDes ]]; then
        duplicateNum=`expr $duplicateNum + 1`
        if (( $duplicateNum > 1 )); then
          duplicateClm="$duplicateClm,$compareSource"
        fi
      fi
    done
  done
  if [[ $duplicateClm == "" ]];then
    echo "exist duplicate column: No" >> $outputFile
  else
    echo "exist duplicate column: Yes -- this may be exception" >> $outputFile
    echo "duplicate column:$duplicateClm" >> $outputFile
  fi
}

#check exist not number value
function checkNotNumberValue() {
  fileName=$1
  columnName=""
  notNumSum=0
  patternArray=(val_response_batch_SFT val_response_batch_OTC val_response_batch_EQUITY)
  for idex in "${patternArray[@]}"; do
    if [[ $fileName =~ $idex ]]; then
      columnName=`zcat $filePath/$fileName|head -1|awk -F "|" '{print$26}'`
      notNumSum=`zcat $filePath/$fileName|awk -F "|" '{print$26}'|grep '[a-Z]$'|wc -l`
#      notNumSum=`zcat $filePath/$fileName|awk -F "|" '{print$26}'|grep -c '[a-Z]$'`
      notNumSum=`expr $notNumSum - 1`
      if (( $notNumSum > 0 ));then
        echo "column $columnName exist not number value:Yes -- this may be exception" >> $outputFile
      else
        echo "column $columnName exist not number value:No" >> $outputFile
      fi
    fi
  done
}

export filePath=$1
newColumnNameFeature=$2
outputFile=./precheck_output.log
files=$(ls ${filePath})
executionTime=`date "+%Y%m%d%H%M"`
startTime_s=`date "+%s"`
echo "file checking ...;start time:$executionTime" > $outputFile
echo "file path:$filePath" >> $outputFile
gzfilePattern=".gz"
fileCount=`ls ${filePath}|wc -l`
echo "file total sum : $fileCount" >> $outputFile
endChar="#"
fileNum=0

for fileName in ${files}
do
  if [[ $fileName =~ $gzfilePattern ]]; then
    fileNum=`expr $fileNum + 1`
    echo -e "\n fileName:$fileName" >> $outputFile

    #check empty file
    count=`zcat $filePath/$fileName | wc -l`
    if ((0==$count))||((1==$count)); then
      echo "empty file:Yes" >> $outputFile
      continue
    else
      echo "empty file:No"
    fi

    #check new column exist
    newColumnExistCheck $fileName

    #check duplicate column
    checkDuplicateColumn $fileName

    #check if exist not numeric value
    checkNotNumberValue $fileName

    #check trailer line
    tailLine=`zcat $filePath/$fileName | tail -2`
    lastLineFirstChar=${tailLine:0:1}
    if [[ $lastLineFirstChar == "#" ]]; then
      echo "no trailer: No" >> $outputFile
    else
      echo "no trailer: Yes" >> $outputFile
      continue
    fi

    #check if line sum match trailer number
    sumMatchCheck $fileName
fi
done

endTime=`date "+%Y%m%d%H%M"`
endTime_s=`date "+%s"`
takeTime=`expr $endTime_s - $startTime_s`
takeTime=`expr $takeTime / 60`
echo -e "\nfile checking end, fileCheckedSum=$fileNum, end time=$endTime, take_time=$takeTime" >> $outputFile
echo "file checking finish"
