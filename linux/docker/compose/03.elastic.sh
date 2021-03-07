#!/bin/bash -v

# sysctl -w vm.max_map_count=262144

# 9200 - Elastic HTTP Rest endpoint
# 9300 - TCP

docker run -d \
  -p 9200:9200 \
  -p 9300:9300 \
  -e "discovery.type=single-node" \
  -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" \
  -v /nosql/elastic/data:/usr/share/elasticsearch/data \
  elasticsearch:7.4.0
