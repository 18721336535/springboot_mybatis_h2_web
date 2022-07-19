cat app.log|grep -C 100 'keyword'
grep -C 10 "keyword" app.log
zcat app.log.zp|grep keyword
zcat app.log.zp|grep -C 3 keyword

