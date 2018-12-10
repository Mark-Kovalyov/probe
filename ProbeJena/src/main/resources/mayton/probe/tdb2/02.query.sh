#!/usr/bin/env bash -v

JENA_ROOT=/jena/current
PROJECT_HOME=~/git/probe/ProbeJena
DB_HOME=/db/tdb2

cd $JENA_ROOT
cd bin

./tdbquery --loc=$DB_HOME --results=text --time --base=http://localhost/# --query=$PROJECT_HOME/src/main/resources/mayton/probe/tdb2/02.query.sparql


