#!/bin/bash
NLS_NUMERIC_CHARACTERS=.
NLS_LANG=american_america.CL8MSWIN1251

sqlldr userid=geoip@xe control=03.GeoIpCity.ctl log=03.GeoIpCity.log bad=03.GeoIpCity.bad direct=yes skip=1 errors=1

