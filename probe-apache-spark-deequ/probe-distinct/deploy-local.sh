#!/bin/bash -v

cp /home/mayton/.m2/repository/org/slf4j/slf4j-api/1.7.30/slf4j-api-1.7.30.jar ./lib/

sbt packageBin

$SPARK_HOME/bin/spark-submit -v \
          --name GeoIpOrc \
          --deploy-mode cluster \
          --master spark://localhost:4040 \
          --class mayton.spark.Main \
          --total-executor-cores 2 \
          --executor-memory 4g \
          target/scala-2.12/GeoIpOrc.jar

