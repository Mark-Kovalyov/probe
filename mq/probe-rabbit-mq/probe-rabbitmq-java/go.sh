#!/bin/bash

mvn clean package -DskipTests

mvn dependency:copy-dependencies -DoutputDirectory=target

cd target

java -jar probe-rabbitmq-java-1.0-SNAPSHOT.jar

cd ..


