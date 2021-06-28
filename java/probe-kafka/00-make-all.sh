#!/bin/bash -ev

# Clean
rm -f bin/*

# Producer + libs
mvn clean package -P kafka-producer -DskipTests
mvn install dependency:copy-dependencies -P kafka-producer
cp target/producer.jar ./bin
rsync target/dependency/* ./bin -r

# Consumer
mvn package -P kafka-consumer -DskipTests
mvn install dependency:copy-dependencies -P kafka-consumer
cp target/consumer.jar ./bin
rsync target/dependency/* ./bin -r