#!/bin/bash

docker run -p 8086:8086 \
           -p 2003:2003 \
           -p 8083:8083 \
    -e INFLUXDB_GRAPHITE_ENABLED=true \
    -e INFLUXDB_ADMIN_ENABLED=true \
    influxdb:1.5.4
