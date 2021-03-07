#!/bin/bash -v

psql -c '\COPY geoipcuty FROM 03.GeoIPCity.csv CSV HEADER';

