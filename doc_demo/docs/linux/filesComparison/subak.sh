#!/bin/bash
echo -e "Hello|World|Yes%Hello||NiHao|Ya\nBello|World|Yes%Bello|NiHao|Ya|" | awk -F"%" '{print $1}'
str="Hello|World|Yes|Hello|NiHao|Ya"
str1=${str//\|/ }
arry=($str1)
num=${#arry[*]}
hfnm=num/2
echo "===$num"
for (( i=0;i<=num/2;i++))
do
  echo ${arry[i]}
  echo ${arry[i+hfnm]}
done

