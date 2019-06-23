#!/usr/bin/env bash -v

JENA_ROOT=/jena/current
PROJECT_HOME=~/git/probe/ProbeJena
DB_HOME=/db/tdb2

cd $JENA_ROOT
cd bin

./tdbdump -loc=$DB_HOME
