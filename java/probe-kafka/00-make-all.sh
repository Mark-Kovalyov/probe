#!/bin/bash -ev

# Clean
rm -f bin/*

# Producer + libs
mvn clean package -P kafka-producer -DskipTests
mvn install dependency:copy-dependencies -P kafka-producer -DskipTests
cp target/producer.jar ./bin
rsync target/dependency/* ./bin -r

# Consumer
mvn clean package -P kafka-consumer -DskipTests
mvn install dependency:copy-dependencies -P kafka-consumer -DskipTests
cp target/consumer.jar ./bin
rsync target/dependency/* ./bin -r