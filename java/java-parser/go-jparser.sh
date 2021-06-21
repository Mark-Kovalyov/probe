#!/bin/bash -ev

rm -fr bin

mkdir bin

mvn clean package -DskipTests
mvn install dependency:copy-dependencies

cp -f target/jparser.jar ./bin
rsync -avh target/dependency/* ./bin --delete

mvn clean package -DskipTests -Dtargetbinary=dhtgetpeers
cp -f target/jparser.jar ./bin

cd ./bin

java -jar jparser.jar --inputFolder /storage/git.java/hadoop

