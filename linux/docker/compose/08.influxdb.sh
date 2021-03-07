#!/bin/bash

version=1.5.4

# The following ports are important and are used by InfluxDB.
#
#    8086 HTTP API port
#    8083 Administrator interface port, if it is enabled
#    2003 Graphite support, if it is enabled

docker run -p 8087:8086 \
           -p 2003:2003 \
           -p 8083:8083 \
    -e INFLUXDB_GRAPHITE_ENABLED=true \
    -e INFLUXDB_ADMIN_ENABLED=true \
    influxdb:$version

#CLI / SHELL

#Start the container:

#docker run --name=influxdb -d -p 8086:8086 influxdb

#Run the influx client in this container:

#$ docker exec -it influxdb influx

#Or run the influx client in a separate container:

#$ docker run --rm --link=influxdb -it influxdb influx -host influxdb
