#!/bin/bash

java \
  -XX:+UnlockExperimentalVMOptions \
  -XX:+UseEpsilonGC \
  -Xmx5120M \
  -jar target/probe-gc-1.0-SNAPSHOT.jar
