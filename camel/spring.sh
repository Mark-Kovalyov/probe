#!/bin/bash -v

mvn archetype:generate \
  -DarchetypeGroupId=org.apache.camel.archetypes \
  -DarchetypeArtifactId=camel-archetype-spring \
  -DarchetypeRepository=https://repository.apache.org/content/groups/snapshots-group \
  -DinteractiveMode=false \
  -DgroupId=mayton.camelspring \
  -DartifactId=camelArchetypeSpringExample 

