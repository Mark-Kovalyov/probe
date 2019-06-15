#!/usr/bin/env bash

javac -version

gradle -version

gradle clean build

# This is simplest to configure and gives the best performance. To make all loggers asynchronous, add the disruptor jar to the
# classpath and set the system property log4j2.contextSelector to org.apache.logging.log4j.core.async.AsyncLoggerContextSelector.

java -Dlog4j2.contextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector \
     -Dlog4j.configurationFile=log4j2-failover-appender.xml \
     -jar build/libs/ProbeLog4j2-1.0-SNAPSHOT.jar 2>&1 | tee failover-logger.log
