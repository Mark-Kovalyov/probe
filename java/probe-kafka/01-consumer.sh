#!/bin/bash -ev

java -jar bin/consumer.jar \
 --bootstrap-servers "localhost:9092" \
 --poll-time 500 \
 --topic-pattern "math.+" \
 --group-id "0"

