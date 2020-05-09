#!/bin/bash -v

gradle clean build
gradle copyToLib

# Jars

cp -f ./build/libs/ignite-prob-1.0-SNAPSHOT.jar $IGNITE_HOME/libs/

cp -f ./build/libs/slf4j-api-1.7.26.jar         $IGNITE_HOME/libs/
cp -f ./build/libs/slf4j-log4j12-1.7.7.jar      $IGNITE_HOME/libs/
cp -f ./build/libs/slf4j-api-1.7.26.jar         $IGNITE_HOME/libs/

cp -f ./build/libs/log4j-api-2.13.2.jar  $IGNITE_HOME/libs/
cp -f ./build/libs/log4j-core-2.13.2.jar $IGNITE_HOME/libs/

# Config

cp -f ./config/main-equity.xml $IGNITE_HOME/config/


