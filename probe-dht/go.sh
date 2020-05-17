#!/bin/bash

mvn clean package -DskipTests

mvn dependency:copy-dependencies -DoutputDirectory=target
cp sensitive.properties target/

cd target

java -jar probe-dht-1.0-SNAPSHOT.jar

cd ..


