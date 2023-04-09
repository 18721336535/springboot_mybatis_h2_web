#!/bin/bash
cat a_1.csv | awk -F',' '{if($2=="A")print (($1-32)*5/9)",C";else print $1","$2}'
diff a_1.csv b_1.csv
sort  c_1.csv
comm a_1.csv b_1.csv
comm -12 <(sort a_1.csv) <(sort a_1_1.csv)
cat sales.csv | awk -F',' '{print $1}' | sort | uniq
Boots

Pants

Shirt

Shoes

Socks

cat sales.csv | awk -F',' '{print $1}' | sort | uniq -c
1 Boots

1 Pants

1 Shirt

2 Shoes

3 Socks

Tr 命令是一个可以移除或替换单个字符或字符集的工具

echo -e "Hello|World|Yes%Hello|NiHao|Ya\nBello|World|Yes%Bello|NiHao|Ya"|grep '%'| while read -r line ; do
    echo "Processing $line"
    ayy1=${line//%/\|}
    ayy1=${ayy1//\|/ }
    num=${#ayy1[*]}
    for i in num/2
    do
      echo "数组的元素为0: $i"
#      if [[ ayy1[i] == ayy1[num-- ]]; then
#        echo "same"
#      fi
    done
#    echo "数组的元素为0: ${ayy1[0]}"
#    echo "数组的元素为1: ${ayy1[1]}"
#    echo "数组的元素个数为: ${#ayy1[*]}"
done