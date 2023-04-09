#!bin/bash
#create on 20230406 by zbq

#-------------usage description---------------------------------------------------------------
#this script is designed to sum specified columns between two files, and compare them
#cmd: bash sum_base_on_specified_columns.sh filePath1 fileName1 filePath2 fileName2 columnIndexs
#example cmd:
#bash sum_base_on_specified_columns.sh /home/zbq 1_bc.dat.gz /home/zbq 2_ac.dat.gz 7
#bash sum_base_on_specified_columns.sh /home/zbq 1_bc.dat.gz /home/zbq 2_ac.dat.gz 3@5
#bash sum_base_on_specified_columns.sh /home/zbq 1_bc.dat.gz /home/zbq 2_ac.dat.gz 3@5@6@9@11
#-------------usage description---------------------------------------------------------------

  #based on each column, sum each column values
  function sumcalculation() {
    ${openMethod} $1/$2|sed '$d'|awk -v arry=$columnIndexs -v akoutputFile=$sumresultFile '
      BEGIN {
        FS="|"
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

  function compareSumResult() {
    echo -e "\nBC/AC sum compare result:">> $sumresultFile
    awk -v arry=$columnIndexs -v akoutputFile=$sumresultFile '
      BEGIN {
        FS="|";
        OFS="|";
        split(arry,arrys,"@");
        for(i in arrys){
          aksumarrys1[arrys[i]]=0;
          aksumarrys2[arrys[i]]=0;
        }
      }

      {
        if (NR == 7){
          for(i in arrys){
            aksumarrys1[arrys[i]]=$i;
            print i"--aksumarrys_ac-"aksumarrys1[arrys[i]];
          }
        }
        if (NR == 10){
          for(i in arrys){
            aksumarrys2[arrys[i]]=$i;
            print i"--aksumarrys_ac-"aksumarrys2[arrys[i]];
          }
        }
      }

      END {
        for(x in arrys){
          if(aksumarrys1[arrys[x]] != aksumarrys2[arrys[x]]){
            print "column-"arrys[x]"-bc/ac sum is diff:("aksumarrys1[arrys[x]]","aksumarrys2[arrys[x]]")">>"./"akoutputFile;
          }else{
            print "column-"arrys[x]"-bc/ac sum is same:("aksumarrys1[arrys[x]]","aksumarrys2[arrys[x]]")">>"./"akoutputFile;
          }
        }
      }' sum_temp.log
  }


filePath=$1
fileName=$2
filePath1=$3
fileName1=$4
columnIndexs=$5
openMethod="cat"
logFileName=${fileName//_bc_/_}
outputFile=./sum_compare_result_${logFileName}.log
sumresultFile=./sum_temp.log

startTime=`date "+%Y%m%d%H%M"`
startTime_s=`date "+%s"`
echo "file comparing...;startTime=$startTime;outPutFile will in file:$outPutFile"

echo "startTime=$startTime" >$outPutFile
echo "source file path:$srcFilePath/$srcFileName" >> $outPutFile
echo "dest file path:$destFilePath/$destFileName" >> $outPutFile
echo "columnIndexs:$columnIndexs" >> $outPutFile

openMethod="cat"
if echo "$fileName"|grep -E -q '\.gz$'
then
openMethod="zcat"
fi

echo -e "\n BC filename :${fileName}" >> $sumresultFile
sumcalculation $filePath $fileName $columnIndexs
echo -e "\n AC filename :${fileName1}" >> $sumresultFile
sumcalculation $filePath1 $fileName1 $columnIndexs

compareSumResult

endTime=`date "+%Y%m%d%H%M"`
endTime_s=`date "+%s"`
takeTime=`expr $endTime_s - $startTime_s`
takeTime=`expr $takeTime / 60`
echo -e "\nfile comparing end, fileCheckedSum=$fileNum, end time=$endTime, take_time=$takeTime" >> $outputFile
echo "file comparing finish"