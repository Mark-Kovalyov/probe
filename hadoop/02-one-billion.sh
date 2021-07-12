#!/bin/bash -ev

mkdir -p bin
rm -f bin/*

mvn clean

mvn package -P onebillion
mvn install dependency:copy-dependencies -P onebillion
cp -f target/one-billion-sequence-gen.jar ./bin
rsync target/dependency/* ./bin -r

java -jar bin/one-billion-sequence-gen.jar

