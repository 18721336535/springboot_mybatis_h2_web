pid=$(ps -ef | grep projectName | grep -v grep|awk ''{print $2})
kill -9 $pid

ps -ef | grep keyword