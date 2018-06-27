#!/bin/bash -v

mvn archetype:generate \
  -DarchetypeGroupId=org.apache.camel.archetypes \
  -DarchetypeArtifactId=camel-archetype-java \
  -DarchetypeVersion=2.9.0 \
  -DarchetypeRepository=https://repository.apache.org/content/groups/snapshots-group \
  -DinteractiveMode=false \
  -DgroupId=mayton.java \
  -DartifactId=camelArchetypeJavaExample

