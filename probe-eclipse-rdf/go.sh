#!/bin/bash -v

nice -n 1 \
   java \
     -Dcom.sun.management.jmxremote \
     -XX:+UseG1GC \
     -XX:+HeapDumpOnOutOfMemoryError \
     -XX:+PrintClassHistogram \
     -Xmx2G \
     -XX:+PrintGCDetails \
     -Xloggc:./logs/gc.log \
     -jar target/ProbeEclipseRdf-1.0-SNAPSHOT.jar

