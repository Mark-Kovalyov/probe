#!/bin/bash

java -jar $AVRO_HOME/avro-tools-1.9.1.jar compile schema geoIpCitySchema.avsc .
