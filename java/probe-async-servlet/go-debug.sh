#!/bin/bash

mvn clean package

java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005 \
 -jar target/probe-async-servlet-1.0.jar
