#!/bin/bash

find . -name '*png' -exec convert -compress none "{}" "{}.pgm" \;

find . -name '*jpg' -exec convert -compress none "{}" "{}.pgm" \;

