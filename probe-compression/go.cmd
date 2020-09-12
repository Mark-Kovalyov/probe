export LIMIT=100
export SELECTION=ORDERED

call mvn clean package

mkdir trie

java -jar target\probe-compression-1.0-SNAPSHOT.jar %LIMIT% %SELECTION%
