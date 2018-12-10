#!/usr/bin/env bash -v

# 1. Install Apache Jena into JENA_ROOT
# 2. PROJECT_

JENA_ROOT=/jena/current
PROJECT_HOME=~/git/probe/ProbeJena
DB_HOME=/db/tdb2

cd $JENA_ROOT
cd bin

./tdbloader2 -l $DB_HOME $PROJECT_HOME/input/nibelungs.ttl


