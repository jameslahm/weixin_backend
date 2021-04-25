git pull
mvn install
pid=$(ps -ef | grep java | grep -v 'grep' | awk '{print $2}')
if test -z "$pid" then
    kill -9 $(pid)
fi
nohup java -jar course-0.0.1.jar &

