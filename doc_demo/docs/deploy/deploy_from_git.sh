#！bin/sh
export JAVA_HOME=/opt/jdk/latest
export MAVEN_OPTS="-Xms1g -Xmx16g -Xss50m -XX:NativeMemoryTracking=summary"
export MAVEN_HOME=~/apache-maven-3.8.6
export LOG_PATH=~/log
export WORK_DIR=~/

cd /iem/workspace/temp
rm -rf ./*
curl -I https://codeload.github.com/18721336535/springboot_mybatis_h2_web/zip/refs/heads/master|grep 'etag' > ./deployInfo.log
curl -s --user ${user}:{pwd} -L -o app.zip https://codeload.github.com/18721336535/springboot_mybatis_h2_web/zip/refs/heads/master
unzip app.zip

cd ~
HOME_DIR=${WORK_DIR}
BASE_DIR=${HOME_DIR}repo/app
SETTING_FILE_PATH=~/settings.xml
CURRENT_TIME=$(date "+%Y.%m.%d-%H.%M.%S")
${MAVEN_HOME}/bin/mvn -T 16 --no-snapshot-updates -s ${SETTING_FILE_PATH} clean install -Dmaven.test.skip=true > dp.log
if [ $? -ne 0 ]
then
  echo "build failed"
  exit
fi

ps -ef |grep tomcat|grep app kill -9 TID
rm -rf app-war-1.0.0-SNAPSHOT
#cp /iem/workspace/temp/app/target/app-war-1.0.0-SNAPSHOT.war /iem/middleware/tomacat9/webapps
cd /iem/workspace/temp/app/target/
unzip app-war-1.0.0-SNAPSHOT.war -d /iem/middleware/tomacat9/webapps/app-war-1.0.0-SNAPSHOT

