#!/bin/bash -v

#mvn help:describe -DgroupId=org.somewhere -DartifactId=some-plugin -Dversion=0.0.0
#mvn help:describe -Dplugin=org.apache.maven.plugins:maven-help-plugin

#mvn help:describe -Dplugin=org.codehaus.mojo:cobertura-maven-plugin                > cobertura-maven-plugin.txt
#mvn help:describe -Dplugin=org.codehaus.mojo:cobertura-maven-plugin       -Ddetail > cobertura-maven-plugin-detailed.txt
#mvn help:describe -Dplugin=org.apache.maven.plugins:maven-assembly-plugin          > maven-assembly-plugin.txt
#mvn help:describe -Dplugin=org.apache.maven.plugins:maven-assembly-plugin -Ddetail > maven-assembly-plugin-detailed.txt

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
  echo "$plugin_name[1]"
  mvn help:describe -Dplugin=org.codehouse.mojo:$plugin_name-maven-plugin > "$plugin_name.txt"
done

# Jacoco

mvn help:describe -Dplugin=org.springframework.boot:spring-boot-maven-plugin > spring-boot-maven-plugin.txt




