#!/usr/bin/env bash

M2HOME=~/.m2
AVRO_VERSION=1.8.2

JAVA_HOME=.;$M2HOME/repository/org/slf4j/slf4j-simple/1.7.25/

java -jar $M2HOME/repository/org/apache/avro/avro-tools/$AVRO_VERSION/avro-tools-$AVRO_VERSION.jar compile schema schema/emp.avsc .