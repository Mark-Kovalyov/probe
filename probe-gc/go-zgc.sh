#!/bin/bash

JAVA_OPTS="$JAVA_OPTS -XX:+UnlockExperimentalVMOptions -XX:+UseZGC"

java $JAVA_OPTS -jar target/probe-gc-1.0-SNAPSHOT.jar