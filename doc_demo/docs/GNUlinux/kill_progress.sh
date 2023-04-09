#! bash/sh
#open print all the executed cmd, log cmd,open: set -x , close: set +x
set -x
pid=$(ps -ef | grep projectName | grep -v grep|awk ''{print $2})
kill -9 $pid

ps -ef | grep keyword