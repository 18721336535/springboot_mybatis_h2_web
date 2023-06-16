#!bin/bash
#create on 20230416 by zbq

#-------------usage description---------------------------------------------------------------
#this script is designed to compare two files
#cmd: bash diff_nosort_comparison_bash_on_two_files.sh filePath1 fileName1 filePath2 fileName2
#example cmd:
#bash v2_improved_files_comparison.sh /home/zbq 1_bc.dat.gz /home/zbq 2_ac.dat.gz
#bash v2_improved_files_comparison.sh ./b bc_test_1.csv ./a bc_test_1.csv
#-------------usage description---------------------------------------------------------------

srcFilePath=$1
srcFileName=$2
destFilePath=$3
destFileName=$4

outPutFile=./nosort_diff_result_${srcFileName//_bc_/_}.log

startTime=`date "+%Y%m%d%H%M"`
startTime_s=`date "+%s"`
echo "file comparing...;startTime=$startTime;outPutFile will in file:$outPutFile"

echo "startTime=$startTime" >$outPutFile
echo "source file path:$srcFilePath/$srcFileName" >> $outPutFile
echo "dest file path:$destFilePath/$destFileName" >> $outPutFile

openMethod="cat"
IFS_OLD=$IFS

if echo "$srcFileName"|grep -E -q '\.gz$'
then
openMethod="zcat"
fi

headline=`${openMethod} ./a/ac_test_1.csv|head -1`
headary=(${headline//\|/ })
headsize=${#headary[@]}
headsize1=$((${#headary[@]}+1))
IFS=$'\n'

j=0
echo "diffline foramt:[linenu,columnnu:(srcvalue,desvalue)]">> $outPutFile
for line in $(cat ./b/bc_test_1.csv); do
  IFS='|'
  for value in $line; do
    arry[++j]=$value
  done
  IFS=$'\n'
done

j=0
for line in $(cat ./a/ac_test_1.csv); do
  IFS='|'
  for value in $line; do
    desarry[++j]=$value
  done
  IFS=$'\n'
done

arrysize=${#arry[@]}
diffclms=""
for ((k=1;k<=arrysize;k++));do
  srcvalue=${arry[k]}
  desvalue=${desarry[k]}
  linenum=$(($(($k/$headsize1))+1))
  nline=$(($k%$headsize1))
  if(($nline==0));then
    diffclms="$diffclms\\n"
  fi
  if [[ "$srcvalue" != "$desvalue" ]];then
    diffclms="$diffclms, [line $linenum,column $k:($srcvalue,"$desvalue")]"
  fi
done
if [[ "$diffclms" != "" ]];then
      echo -e "$diffclms" >> $outPutFile
fi
IFS=$IFS_OLD

endTime=`date "+%Y%m%d%H%M"`
endTime_s=`date "+%s"`
takeTime=`expr $endTime_s - $startTime_s`
takeTime=`expr $takeTime / 60`
echo -e "\nfile comparing end,end time=$endTime, take_time=$takeTime" >>$outPutFile
echo "file comparing finish"