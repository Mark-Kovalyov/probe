#!/bin/bash -v

java -jar img2ppm.jar ../../heightmap/volcano.png volcano.ppm

java -jar img2pgm.jar ../../heightmap/volcano.png volcano.pgm