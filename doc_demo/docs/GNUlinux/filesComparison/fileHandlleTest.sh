#!bin/bash

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

openMethod="cat"

if echo "$srcFileName"|grep -E -q '\.gz$'
then
openMethod="zcat"
fi


linesum=`${openMethod} $1/$2|wc -l`
readSum=(($linesum / 10000))
startline=0
endline=10000
for((i=0;i<$readSum;i++)){

  startline=(($startline + 10000))
  endline=(($endline + 10000))
  if(($startline>$linesum));then

  fi

  sed -n '$starttline,"${endline}"p'

  while read -r line; do

  done <()
}




for((j=1;j<4;j++)){

  for((i=0;i<10;i++)){

#    if(($j==1)); then
       B1[i]="$j"$i

#    fi

#    if(($j==2)); then
#         B2[i]="$j"$i
#         pry[j]=B2
#    fi
#
#    if(($j==3)); then
#         B3[i]="$j"$i
#         pry[j]=B3
#    fi
  }
  pry[j]=B1

}

#for((i=0;i<10;i++)){
#    mry2[i]="B"$i
#}
#
#for((i=0;i<10;i++)){
#    mry3[i]="C"$i
#}
#
#pry=(mry1 mry2 mry3)

for k in ${pry[@]}; do
  echo $k
  temp=$k[@]
  tempsub=(${!temp})
  for sry in ${tempsub[@]} ;do
    echo $sry
  done

done

endTime=`date "+%Y%m%d%H%M"`
endTime_s=`date "+%s"`
takeTime=`expr $endTime_s - $startTime_s`
takeTime=`expr $takeTime / 60`
echo -e "\nfile comparing end, end time=$endTime, take_time=$takeTime" >> $outputFile
echo "file comparing finish"