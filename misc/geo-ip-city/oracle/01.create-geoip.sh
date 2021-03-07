#!/bin/bash

sqlplus sys@"xe as sysdba" @01.create-geoip.sql < syspwd 