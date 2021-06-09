#!/bin/bash -e

cdate=$(date +"%Y-%m-%d-%H-%M-%S")

mvn clean package
mvn install dependency:copy-dependencies -P frameworked
cp -f target/frameworked.jar ./bin
rsync target/dependency/* ./bin -d -r

echo "Compillation status = $?"

java -jar bin/frameworked.jar -s "/storage/db/GEO/maxmind/2010-10.MaxMind GeoIP City CSV Format/GeoIP-139_20101001/GeoIPCity.csv.gz" -d output.json