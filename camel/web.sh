#!/bin/bash -v

mvn archetype:generate \
  -DarchetypeGroupId=org.apache.camel.archetypes \
  -DarchetypeArtifactId=camel-archetype-web \
  -DarchetypeRepository=https://repository.apache.org/content/groups/snapshots-group \
  -DinteractiveMode=false \
  -DgroupId=mayton \
  -DartifactId=camelArchetypeWebExample 
