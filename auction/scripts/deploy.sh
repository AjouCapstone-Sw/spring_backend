#!/bin/bash
BUILD_JAR=$(ls /home/ubuntu/api-server/*.jar)
JAR_NAME=$(basename $BUILD_JAR)
echo "> build 파일명: $JAR_NAME" >> /home/ubuntu/api-server/deploy.log

echo "> build 파일 복사" >> /home/ubuntu/api-server/deploy.log
DEPLOY_PATH=/home/ubuntu/api-server/
cp $BUILD_JAR $DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 pid 확인" >> /home/ubuntu/api-server/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ubuntu/api-server/deploy.log
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
chmod +x $DEPLOY_JAR

echo "> pm2 배포"    >> /home/ubuntu/api-server/deploy.log
pm2 start $DEPLOY_PATH/app.json >> /home/ubuntu/deploy.log 2>/home/ubuntu/api-server/deploy_err.log &


#echo "> DEPLOY_JAR 배포"    >> /home/ubuntu/api-server/deploy.log
#nohup java -jar $DEPLOY_JAR >> /home/ubuntu/deploy.log 2>/home/ubuntu/api-server/deploy_err.log &