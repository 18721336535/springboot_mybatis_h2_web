#!/bin/bash

echo  -e "5|2|3\n4|5|6"|awk -F "|" 'BEGIN {
a[0]=1;a[1]=2;a[2]=3;

fruits["mango"]="yellow";
fruits["orange"]="orange";
print fruits["orange"] "\n" fruits["mango"];
for(i in a){
print i "-xxx-"a[i];
b[a[i]]=0;
}
print "-2222bbb2-"b[1]b[2]b[3];
}

{
for(i in a){
b[a[i]]=b[a[i]]+$a[i];
print i"-ai="a[i] ";$a[i]="$a[i]"-b[a[i]]="b[i];
}

}


END {print "-b[1]-"b[1]"-b[2]-"b[2]"-b[3]-"b[3];}'

#cd /d/workspace/gitProjects/doc_demo/docs/linux/params_files
#$ bash awk1.sh 3@4@5
#arry0--3@4@5
#arry2--4
#1arryi--3
#2arryi--4
#3arryi--5
#1-arryresult[arrys[i]]--0
#2-arryresult[arrys[i]]--0
#3-arryresult[arrys[i]]--0
#1-arryresult[arrys[i]]---100
#2-arryresult[arrys[i]]--1000
#3-arryresult[arrys[i]]--1.1
#1-arryresult[arrys[i]]---1101
#2-arryresult[arrys[i]]--2000
#3-arryresult[arrys[i]]--3.2
#1-arryresult[arrys[i]]---100
#2-arryresult[arrys[i]]--7000
#3-arryresult[arrys[i]]--2.2
#3-end-arryresult---100
#4-end-arryresult--7000
#5-end-arryresult--2.2
