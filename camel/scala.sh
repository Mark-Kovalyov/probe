#!/bin/bash -v

mvn archetype:generate \n
  -DarchetypeGroupId=org.apache.camel.archetypes \n
  -DarchetypeArtifactId=camel-archetype-scala \n
  -DarchetypeRepository=https://repository.apache.org/content/groups/snapshots-group \n
  -DinteractiveMode=false \n
  -DgroupId=mayton.scala \n
  -DartifactId=camelArchetypeScalaExample \n

