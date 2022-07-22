#check the connections from the current host to target host.午物务
telnet [targetHost] [port] //also able to get ip
netstat -anl| grep ip | wc -l