#!/bin/bash -v

$SPARK_HOME/bin/spark-shell \
  --master "local[4]" \
  --driver-memory 4G

