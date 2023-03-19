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