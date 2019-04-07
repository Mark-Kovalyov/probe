#!/bin/bash -v

mvn clean compile exec:java -P geo -Dgeo.input.file=~/GeoIPCity.csv
