#!/bin/bash -v

mvn clean package

java -jar target/dig-hamster-1.0-SNAPSHOT.jar
