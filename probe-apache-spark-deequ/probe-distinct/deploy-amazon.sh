#!/bin/bash -v

aws emr add-steps --cluster-id j-2AXXXXXXGAPLF \
 --steps Type=Spark,Name="Spark Program",ActionOnFailure=CONTINUE,Args=[--class,org.apache.spark.examples.SparkPi,/usr/lib/spark/examples/jars/spark-examples.jar,10]

aws emr add-steps --cluster-id j-2AXXXXXXGAPLF \
 --steps Type=CUSTOM_JAR,Name="Spark Program",Jar="command-runner.jar",ActionOnFailure=CONTINUE,Args=[spark-example,SparkPi,10]