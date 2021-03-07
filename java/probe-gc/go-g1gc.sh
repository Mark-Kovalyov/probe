#!/bin/bash

java \
  -XX:+UseG1GC \
  -jar target/probe-gc-1.0-SNAPSHOT.jar
