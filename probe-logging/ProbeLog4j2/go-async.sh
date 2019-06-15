#!/usr/bin/env bash

javac -version

gradle -version

gradle clean 
gradle copyToLib
gradle build

java -verbose:class \
     -Dlog4j2.contextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector \
     -Dlog4j.configurationFile=log4j2-async-appender.xml \
     -jar build/libs/ProbeLog4j2-1.0-SNAPSHOT.jar 2>&1 | tee acync-logger.log

cat acync-logger.log | \
 grep -i -F "[info][class,load]" | \
 grep -v -i -F "[info][class,load] java." > acync-logger-class-loader.log
