#!/bin/bash -v

psql "postgresql://geoip:geoip@localhost/geoip" -f 01.create-geoipcity.sql
