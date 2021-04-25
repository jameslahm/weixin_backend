git pull
mvn install
pid=$(ps -ef | grep -v "grep" java | awk '{print $2}')
kill -9 $(pid)
nohup java -jar course-0.0.1.jar &

