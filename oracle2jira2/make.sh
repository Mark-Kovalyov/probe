#!/bin/bash

mvn clean package

cp ./target/*.jar .

java -XX:+UseSerialGC -jar SQL2Horizontal.jar "scott/tiger@127.0.0.1:1521/XE" "select * from emp order by 1"

java -XX:+UseSerialGC -jar SQL2Vertical.jar   "scott/tiger@127.0.0.1:1521/XE" "select * from emp order by 1"