#!/bin/bash -v

java -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC -jar target\json-check-1.0-SNAPSHOT.jar $*

