#!/bin/bash
#bash awk.sh 1@2@3
str=$1
awk -v arry=$1 '
BEGIN {
FS="|";
OFS=":";
split(arry,arrys,"@");
print "arry0--"arry;
print "arry2--"arrys[2];
for(i in arrys){
print i"arryi--"arrys[i];
arryresult[arrys[i]]=0;
}
}

{
for(i in arrys){
arryresult[arrys[i]]=arryresult[arrys[i]]+$arrys[i];
print i"-arryresult[arrys[i]]--"arryresult[arrys[i]];
}
}

END {
for(i in arryresult){
print i"-end-arryresult--"arryresult[i];
}
#print "end resultArray--"arryresult[arrys[1]"--"arryresult[arrys[2]"--"arryresult[arrys[3]];
#print "end resultArray--"arryresult[arrys[1]"--"arryresult[arrys[2]"--"arryresult[arrys[3]] >"./xx.log";
print "end--"NR >"./xx.log";}
' table_list_export.txt



##!/bin/bash
#
#echo  -e "5|2|3\n4|5|6"|awk -F "|" 'BEGIN {
#a[0]=1;a[1]=2;a[2]=3;
#b[1]=0;
#fruits["mango"]="yellow";
#fruits["orange"]="orange";
#print fruits["orange"] "\n" fruits["mango"];
#for(i in a){
#print i "-xxx-"a[i];
#b[a[i]]=0;
#}
#print "-2222bbb2-"b[1]b[2]b[3];
#}
#{
#for(i in a){
#b[a[i]]=b[a[i]]+$a[i];
#print i"-ai="a[i] ";$a[i]="$a[i]"-b[a[i]]="b[i];
#}
#}
#END {print "-b[1]-"b[1]"-b[2]-"b[2]"-b[3]-"b[3];}'

