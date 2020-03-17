#!/bin/bash -v

cp /home/mayton/.m2/repository/org/slf4j/slf4j-api/1.7.30/slf4j-api-1.7.30.jar ./lib/

mvn clean package

export SPARK_HOME=/spark/2.4.5-bin-without-hadoop-scala-2.12/

$SPARK_HOME/bin/spark-submit -v \
          --name DistinctExample01 \
          --deploy-mode client \
          --master spark://localhost:4040 \
          --class mayton.spark.Main \
          --total-executor-cores 2 \
          --executor-memory 4g \
          --jars lib/slf4j-api-1.7.30.jar \
          target/scala-2.12/probe-distinct-java-1.0.jar
