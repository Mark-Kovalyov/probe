call mvn clean package

xcopy target\probe-markov-1.0-SNAPSHOT.jar .

java -jar probe-markov-1.0-SNAPSHOT.jar