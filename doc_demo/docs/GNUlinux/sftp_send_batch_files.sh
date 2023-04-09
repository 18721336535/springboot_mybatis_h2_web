#!/bin/bash
echo "start to execute shell script,params:$1,$2,$3,$4,$5"
uid=$1
hostname=$2
dataPath=$3
shPath=$4
targetDir=$5
runDate=`date "+%Y%m%d"`
LOGDIR=${dataPath}/log;export LOGDIR
logFile=${LOGDIR}/send_batch_file.sh.log.${runDate}
touch ${logFile}
chmod 744 ${logFile}
if [! -d ${LOGDIR} ]; then
  mkdir -p $LOGDIR
  chmod -R 744 ${dataPath}/*
fi
export uid hostname dataPath shPath targetDir runDate
echo "send_batch_file start at :`date "+%Y-%m-%d %H:%M:%S"`"
. ${shPath}/generate_send_data_file.sh $runDate<${shPath}/config/table_list_exported.txt>>$logFile
exitVal=$?
if [ "$exitVal" -ne "0"];then
  echo "send batch files fail"
  exit -1
else
  echo "send_batch_file send successfully end at :`date "+%Y-%m-%d %H:%M:%S"`"
  exit 0
fi


