#!bin/bash
#create on 20230331 by zbq

#-------------usage description---------------------------------------------------------------
#this script is designed to compare two files
#cmd: bash diff_nosort_comparison_bash_on_two_files.sh filePath1 fileName1 filePath2 fileName2
#example cmd:
#bash diff_nosort_comparison_bash_on_two_files.sh /home/zbq 1_bc.dat.gz /home/zbq 2_ac.dat.gz
#-------------usage description---------------------------------------------------------------

srcFilePath=$1
srcFileName=$2
destFilePath=$3
destFileName=$4

outPutFile=./nosort_diff_result_${srcFileName/_bc_/_}.log

startTime=`date "+%Y%m%d%H%M"`
startTime_s=`date "+%s"`
echo "file comparing...;startTime=$startTime;outPutFile will in file:$outPutFile"

echo "startTime=$startTime" >$outPutFile
echo "source file path:$srcFilePath/$srcFileName" >> $outPutFile
echo "dest file path:$destFilePath/$destFileName" >> $outPutFile
>${srcFileName}.log
openMethod="cat"
if echo "$srcFileName"|grep -E -q '\.gz$'
then
openMethod="zcat"
fi

diff --suppress-common-lines <(${openMethod} $srcFilePath/$srcFileName) <(${openMethod} $destFilePath/$destFileName) -y -w 800 -H >> ./${srcFileName}.log
#diff --suppress-common-lines $srcFilePath/$srcFileName $destFilePath/$destFileName -y -w 800 -H >> ./${srcFileName}.log

#grep '  <' $srcFileName.log|sed -r 's/[[:blank]]//g' >> $outPutFile
#grep '>  ' $srcFileName.log|sed -r 's/[[:blank]]//g' >> $outPutFile
echo -e "\n-------lines existing diff between src and dest---" >> $outPutFile
grep -E '( \| )+' ./$srcFileName.log|while read -r line; do
  echo -e "\n${line//\| /\\n}" >> $outPutFile
  srcline=`echo "$line"|sed -r 's/\|  /@/g'|cut -d '@' -f 1`
  srcline=`echo "$srcline"|sed -r 's/[[:blank:]]//g'`
  srclinespt=${srcline//\|/ }
  srclineAry=($srclinespt)
  halfArrySize=${#srclineAry[*]}

  trimLine=`echo "$line"|sed -r 's/[[:blank:]]//g'`
  lineSplitStr=${trimLine//\|/ }
  lineArray=($lineSplitStr)

  j=halfArrySize
  diffIndexArray=""
  for (( i=0,j=halfArrySize; i<halfArrySize;i++,j++))
  do
    if [[ ${lineArray[i]} != ${lineArray[j]} ]]; then
      $diffIndexArray="$diffIndexArray,$i(${lineArray[i]}:${lineArray[j]})"
    fi
  done
  if [[ $diffIndexArray != ""]];then
    echo "diff clms index(srcvalue:destvalue) :$diffIndexArray" >> $outPutFile
  fi
done

endTime=`date "+%Y%m%d%H%M"`
endTime_s=`date "+%s"`
takeTime=`expr $endTime_s - $startTime_s`
takeTime=`expr $takeTime / 60`
echo -e "\nfile comparing end, end time=$endTime, take_time=$takeTime" >> $outputFile
echo "file comparing finish"


