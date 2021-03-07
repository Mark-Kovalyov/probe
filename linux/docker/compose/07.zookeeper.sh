#!/bin/bash -v

# 2181 the zookeeper client port
# 2888 follower port
# 3888 election port
# 8080 AdminServer port

# 
# Connect to Zookeeper from the Zookeeper command line client
#
# $ docker run -it --rm --link some-zookeeper:zookeeper zookeeper zkCli.sh -server zookeeper

docker run -d \
 -e ZOO_LOG4J_PROP="INFO,ROLLINGFILE" \
 -v /zookeeper/docker/zoo.cfg:/conf/zoo.cfg \
 -p 2181:2181 \
 -p 2888:2888 \
 -p 3888:3888 \
 -p 8080:8080 \
 zookeeper:3.4


