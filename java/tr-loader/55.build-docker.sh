#!/bin/bash -v

mvn clean package

docker build \
 -f Dockerfile \
 -t tr-loader .
