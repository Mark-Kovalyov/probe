#!/bin/bash -v

psql "postgresql://geoip:geoip@localhost/geoip" -f 02.create-geoipcity.sql
