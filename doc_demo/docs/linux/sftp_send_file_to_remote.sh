#!/bin/bash
echo "start to execute shell script,params:$1,$2,$3,$4,$5"
sourceDir=$1
targetDir=$2
fileName=$3
userId=$4
targetHost=$5
cd ${sourceDir}
touch "${fileName}"
echo "start sftp connect ${userId}@${targetHost}"
sftp -o StrictHostKeyChecking=no ${userId}@${targetHost} << EOF
cd ${targetDir}
lcd ${sourceDir}
put ${fileName}
quit
EOF
exitVal=$?
if [ "$exitVal" -ne "0" ]; then
  echo "sftp quit and shell executed successfully"
else
  echo "sftp quit and shell executed failed"
fi
