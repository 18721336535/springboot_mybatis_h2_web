#!/bin/bash
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