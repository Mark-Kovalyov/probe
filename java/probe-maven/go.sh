#!/bin/bash -v

# Main plugins

for plugin_name in dependency assembly clean resources compiler surefire jar install deploy site project-info-reports enforcer
do
  mkdir -p "maven/$plugin_name"
  echo "# $plugin_name" > "maven/$plugin_name/README.md"
  mvn help:describe -Dplugin=org.apache.maven.plugins:maven-$plugin_name-plugin          >> "maven/$plugin_name/README.md"
  echo "## Detailed Info" >> "maven/$plugin_name/README.md"  
  mvn help:describe -Dplugin=org.apache.maven.plugins:maven-$plugin_name-plugin -Ddetail >> "maven/$plugin_name/README.md"
done

# Mojo group

for plugin_name in cobertura
do
  mkdir -p "mojo/$plugin_name"
  echo "# $plugin_name" > "mojo/$plugin_name/README.md"
  mvn help:describe -Dplugin=org.codehouse.mojo:$plugin_name-maven-plugin >> "mojo/$plugin_name/README.md"
  echo "## Detailed Info" >> "mojo/$plugin_name/README.md"  
  mvn help:describe -Dplugin=org.codehouse.mojo:$plugin_name-maven-plugin -Ddetail >> "mojo/$plugin_name/README.md"
done

# Spring

for plugin_name in spring-boot-maven-plugin 
do
  mkdir -p "spring/$plugin_name"
  echo "# $plugin_name" > "spring/$plugin_name/README.md"
  mvn help:describe -Dplugin=org.springframework.boot:spring-boot-maven-plugin >> "spring/$plugin_name/README.md"
  echo "## Detailed Info" >> "spring/$plugin_name/README.md"
  mvn help:describe -Dplugin=org.springframework.boot:spring-boot-maven-plugin -Ddetail >> "spring/$plugin_name/README.md"  
done






