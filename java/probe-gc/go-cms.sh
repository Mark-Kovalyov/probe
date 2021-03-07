#!/bin/bash

java \
 -XX:+UseConcMarkSweepGC \
 -jar target/probe-gc-1.0-SNAPSHOT.jar