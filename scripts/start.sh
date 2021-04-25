#!/bin/bash
echo "start git pull..."
git pull
echo "git pull done!"

echo "start mvn install"
mvn install
echo "mvn install donw"

echo "start kill previouse pid"
pid=$(ps -ef | grep java | grep -v 'grep' | awk '{print $2}')
if test -z "$pid" then
    echo "kill $(pid)"
    kill -9 $(pid)
fi
echo "kill previous pid down"
echo "start new pid"
nohup java -jar course-0.0.1.jar &
echo "start new pid done"
