#!/bin/bash
sourcePath=$1
destPath=$2
outputFile=./diff_output.txt
files=$(ls ${sourcePath})
executionTime=`date "+%s"`
echo "start executionTime:$executionTime" > $outputFile
gzfilePattern=".gz"

for fileName in ${files}
do
  echo -e "\nfile fileName:$fileName" >> $outputFile
   count=`zcat $sourcePath/$fileName | wc -l`
  echo -e "file count:$count" >> $outputFile
  diffcount=`diff <(zcat $sourcePath/$fileName | sort) <(zcat $destPath/$fileName| sort) -y | grep -c '[<\|>]'`
  echo  "diff line:$diffcount" >> $outputFile
  diffcount=`comm -23 <(zcat $sourcePath/$fileName | sort) <(zcat $destPath/$fileName| sort) | wc -l`
  echo  "diff line:$diffcount" >> $outputFile

done
executionTime=`date "+%s"`
echo "send executionTime:$executionTime" >> $outputFile

#sh  myzdiff.sh D:\\workspace\\gitProjects\\doc_demo\\docs\\linux\\filesComparison\\a D:\\workspace\\gitProjects\\doc_demo\\docs\\linux\\filesComparison\\b