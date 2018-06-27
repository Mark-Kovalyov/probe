#!/bin/bash -v

mvn archetype:generate \
  -DarchetypeGroupId=com.strategicgains.archetype \
  -DarchetypeArtifactId=restexpress-cassandra \
  -DarchetypeVersion=1.18 \
  -DarchetypeRepository=http://repo.maven.apache.org/maven2/ \
  -DinteractiveMode=false \
  -DgroupId=mayton.cassandra \
  -DartifactId=cassandraExample

