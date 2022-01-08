
#!/usr/bin/env bash
if [ $# -lt 4 ];then
	echo "arguments not enough"
fi
echo "pramas are:$1:$2:$3:$4"
ssh
cd $1
