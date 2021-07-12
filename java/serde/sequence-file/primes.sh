#!/bin/bash -ev

cdate=$(date +"%Y-%m-%d-%H-%M-%S")

mkdir -p bin

mvn clean package -P primes
mvn install dependency:copy-dependencies -P primes
cp -f target/primes.jar ./bin
rsync target/dependency/* ./bin -d -r

java -jar bin/primes.jar -s 1 -d 1


