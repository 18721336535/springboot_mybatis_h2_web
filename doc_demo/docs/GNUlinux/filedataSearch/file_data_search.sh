#!bin/bash
#create on 20230406 by zbq

#-------------usage description---------------------------------------------------------------
#this script is designed to search records based on file
#cmd:bash file_data_search.sh [filePath] [fileName] [conditions] [dataSeparator]
#example cmd:
#bash file_data_search.sh ./data.csv '($1=="bz1"&&$2=="rolle2"&&$3>0&&$4==28&&$5<0)' "|"
#result:  bz1|rolle2|2001.00|28|-2.1
#-------------usage description---------------------------------------------------------------

filePath=$1
fileName=$2
conditions=$3
dataSeparator=$4
openMethod="cat"

outPutFile=./result_${fileName}.log
startTime=`date "+%Y%m%d%H%M"`
startTime_s=`date "+%s"`
echo "data searching...;startTime=$startTime;outPutFile will in file:$outPutFile"

echo -e "startTime=$startTime\n" >$outPutFile

if echo "$fileName"|grep -E -q '\.gz$'
then
  openMethod="zcat"
fi

${openMethod} $filePath/$fileName|head -1 >>$outPutFile
${openMethod} $filePath/$fileName|awk -F $dataSeparator "$conditions"'{print $0}' >>$outPutFile


endTime=`date "+%Y%m%d%H%M"`
endTime_s=`date "+%s"`
takeTime=`expr $endTime_s - $startTime_s`
takeTime=`expr $takeTime / 60`
echo -e "\ndata search end, end time=$endTime, take_time=$takeTime" >>$outPutFile
echo "file comparing finish"