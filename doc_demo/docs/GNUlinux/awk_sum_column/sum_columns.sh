#!bin/bash
#create on 20230406 by zbq

#-------------usage description---------------------------------------------------------------
#this script is designed to sum specified columns in file
#cmd: bash sum_columns.sh filePath1 fileName1 filePath2 fileName2 columnIndexs
#example cmd:
#bash sum_columns.sh /home/zbq 1_bc.dat.gz 7
#bash sum_columns.sh /home/zbq 1_bc.dat.gz 3@5
#bash sum_columns.sh /home/zbq 1_bc.dat.gz 3@5@6@9@11
#-------------usage description---------------------------------------------------------------

  #based on each column, sum each column values
  function sumcalculation() {
    ${openMethod} $1/$2|awk -F $dataseparator -v arry=$columnIndexs -v akoutputFile=$sumresultFile '
      BEGIN {
        split(arry,arrys,"@");
        for(i in arrys){
          aksumarrys[arrys[i]]=0;
        }
      }

      {
       if (NR != 1){
          for(i in arrys){
            aksumarrys[arrys[i]]+=$arrys[i];
          }
       }
      }

      END{
        for(j in aksumarrys){
          printf aksumarrys[j]"|" >>"./"akoutputFile
        }
      }'
  }

filePath=$1
fileName=$2
columnIndexs=$3
dataseparator=$4
openMethod="cat"
logFileName=${fileName//_bc_/_}
outputFile=./sum_compare_result_${logFileName}.log
sumresultFile=./sum_temp.log

startTime=`date "+%Y%m%d%H%M"`
startTime_s=`date "+%s"`
echo "file comparing...;startTime=$startTime;outPutFile will in file:$outPutFile"

echo "startTime=$startTime" >$outPutFile
echo "source file path:$srcFilePath/$srcFileName" >> $outPutFile
echo "columnIndexs:$columnIndexs" >> $outPutFile

openMethod="cat"
if echo "$fileName"|grep -E -q '\.gz$'
then
openMethod="zcat"
fi

if [ ! $dataseparator ] ; then
  dataseparator="|"
fi

echo -e "\n BC filename :${fileName}" >> $sumresultFile
sumcalculation $filePath $fileName $columnIndexs


endTime=`date "+%Y%m%d%H%M"`
endTime_s=`date "+%s"`
takeTime=`expr $endTime_s - $startTime_s`
takeTime=`expr $takeTime / 60`
echo -e "\nfile comparing end, fileCheckedSum=$fileNum, end time=$endTime, take_time=$takeTime" >> $outputFile
echo "file comparing finish"