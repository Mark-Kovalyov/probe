#!/bin/bash -v

sed "s/mainClassName/mayton.images.Img2Pgm/g" template.xml | sed "s/artifactName/img2pgm/g" > pom.xml
mvn clean package
cp target/img2pgm.jar release/img2pgm.jar

sed "s/mainClassName/mayton.images.Img2Ppm/g" template.xml | sed "s/artifactName/img2ppm/g" > pom.xml
mvn clean package
cp target/img2ppm.jar release/img2ppm.jar

# TODO: Consider xml-starlet instead of 'sed'
