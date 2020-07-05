#!/bin/bash -v

mvn clean compile exec:java -P geo -Dgeo.input.file=$GEO_IP_CITY_FILE
