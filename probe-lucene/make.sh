mvn clean package -P indexer

cp target/probe-lucene-1.0-SNAPSHOT.jar .

mvn clean package -P bitempIndexer

cp target/probe-lucene-1.0-SNAPSHOT.jar .

