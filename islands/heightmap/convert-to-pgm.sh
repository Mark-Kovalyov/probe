#!/bin/bash

find . -name '*png' exec convert {} {}.pgm \;


