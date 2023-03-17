#!/bin/bash

str="Hello|13| ya!\n
mm|gg|56"
iay=(aa bb cc)
index=0
for idex in "${iay[@]}";do
	index=`expr $index + 1`
	echo -e "aa|1|CC\naa|5|BB\ncc|66|jj"|cut -d '|' -f 2|grep '^[0-9]'
		echo -e "aa|1|CC\naa|5|BB\ncc|66|jj"|cut -d '|' -f 1|grep '^[a-Z]'

done