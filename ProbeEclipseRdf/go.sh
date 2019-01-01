#!/bin/bash -v

rm /db/tdb2/*

rm /db/tdb/*

nice -n 1 \
   java -Dcom.sun.management.jmxremote \
     -XX:+UseG1GC \
     -XX:+HeapDumpOnOutOfMemoryError \
     -XX:+PrintClassHistogram \
     -Xmx2G \
     -XX:+PrintGCDetails \
     -Xloggc:./logs/gc.log \
     -jar target/ProbeEclipseRdf-1.0-SNAPSHOT.jar 2>&1 | tee /logs/boot.log

#jps -l | grep StreamableLoader
#renice -n 20 $val