#!/bin/bash -e

cdate=$(date +"%Y-%m-%d-%H-%M-%S")

mvn clean package
mvn install dependency:copy-dependencies -P profile1
cp -f target/ApplicationName.jar ./bin
rsync target/dependency/* ./bin -d -r

echo "Compillation status = $?"

java -jar bin/ApplicationName.jar