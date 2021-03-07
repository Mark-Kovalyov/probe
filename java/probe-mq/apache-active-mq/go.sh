#!/bin/bash

mvn clean package -DskipTests

mvn dependency:copy-dependencies -DoutputDirectory=target

cd target

java -jar apache-active-mq-*.jar

cd ..


