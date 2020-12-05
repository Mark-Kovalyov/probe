#!/bin/bash -v

psql "postgresql://user:******@localhost/postgres" -f 01.create-geoipcity.sql
