#!/bin/bash -ev

java -jar bin/producer.jar \
 --bootstrap-servers "localhost:9092" \
 --topic "math.primes" \
 --max-primes "100000000"


