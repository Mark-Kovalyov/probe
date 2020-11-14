#!/bin/bash

java \
  -XX:+UseSerialGC \
  -Xmx5120M \
  -jar target/probe-gc-1.0-SNAPSHOT.jar
