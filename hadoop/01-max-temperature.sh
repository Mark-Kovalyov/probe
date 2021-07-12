#!/bin/bash -v

export HADOOP_CLASSPATH=temperature.jar

hadoop MaxTemp input/ncdc/sample.txt output