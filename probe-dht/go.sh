#!/bin/bash

mvn clean package -DskipTests

mvn dependency:copy-dependencies -DoutputDirectory=target

cd target

java -jar probe-dht-1.0-SNAPSHOT.jar 2>&1 | tee probe-dht-1.0-SNAPSHOT.log

cd ..


