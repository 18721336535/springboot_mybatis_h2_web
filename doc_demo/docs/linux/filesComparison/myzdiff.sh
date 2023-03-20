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

#source  myzdiff.sh D:\\workspace\\gitProjects\\doc_demo\\docs\\linux\\filesComparison\\a D:\\workspace\\gitProjects\\doc_demo\\docs\\linux\\filesComparison\\b
#source 执行命令是直接执行命令，不创建子 shell
#sh 创建子 shell。子 shell 里面的变量父 shell 无法使用，对环境变量的修改也不影响父 shell，但是父 shell 里面的变量子 shell 可以使用。子 shell 执行完毕后，回到父 shell，不会影响父 shell 原本的环境
#通过 wihch 命令查看 sh 命令可以发现，sh 只是 bash 的软链接，那为什么和 bash 执行结果有区别呢？
#bash 执行时，如果 $0 是 sh ，则要求脚本内容遵循 POSIX （Portable Operating System Interface of UNIX，缩写为 POSIX ）规范，当不符合规范的语法存在时，则会报错。
#所以当我们使用 sh 执行时，并不是一个程序，而是一种标准（POSIX），
#这种标准，在一定程度上保证了脚本的跨系统性（跨UNIX系统）。
#解决问题
#找到了问题的原因，我们就可以解决了
#如果你仍要使用 sh 的方式执行，那么请在代码的第一行加上
#set +o posix
#命令，这条命令会关闭使用 POSIX 规范的检测