#!/bin/bash -v

java -XX:+UseG1GC \
     -XX:+HeapDumpOnOutOfMemoryError \
     -XX:+PrintClassHistogram \
     -Xmx512M \
     -XX:+PrintGCDetails \
     -Xloggc:./logs/gc.log \
     -jar build/libs/ProbeEclipseRdf.jar
