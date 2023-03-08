#!/bin/sh

checkFileEmpty(){

}


str="12.3"
#endcgae=${str:0:1}
endcgae="#"
echo "--$endcgae"
if [[ $str =~ "." ]]; then
echo "# end"
else
echo " end no #"
fi


val="abccbC\neec"
cc=${val:1:1}
echo "--$cc"
ccounts=`echo  -e $val |grep -c -i 'c'`
echo "ccounts $ccounts"

str1="defff"
str2="def"
if [[ $str1 == "defff" ]]
then
    echo "包含"
else
    echo "不包含"
fi
if [ $ccounts -gt 1 ]; then
echo "true"
fi

string="hello,shell,split,test"
array=(${string//,/ })

for var in ${array[@]}
do
if [[ $var != "shell" ]];then
   echo "it is: $var"
fi
done

