#!/bin/bash -v

mvn clean package 2>&1 | tee compillation.log 

mvn dependency:analyze > dependency-analyze.txt

mvn dependency:tree > dependency-tree.txt

java -Xmx2G -jar target/rdf-to-graphviz-1.0-SNAPSHOT.jar nibelungs.ttl nibelungs.dot 2>&1 | tee logs.lst

