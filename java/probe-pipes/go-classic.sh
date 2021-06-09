#!/bin/bash -e

cdate=$(date +"%Y-%m-%d-%H-%M-%S")

mvn clean package
mvn install dependency:copy-dependencies -P classic
cp -f target/classic.jar ./bin
rsync target/dependency/* ./bin -d -r

echo "Compillation status = $?"

java -jar bin/classic.jar