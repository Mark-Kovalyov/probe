#!/bin/bash

AVRO_HOME=/jlib/avro/1.9.1

java -jar $AVRO_HOME/avro-tools-1.9.1.jar compile schema ../schema/geoIpCitySchema.avsc .
