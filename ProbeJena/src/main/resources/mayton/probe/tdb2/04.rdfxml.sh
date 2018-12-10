#!/usr/bin/env bash

./rdfxml --out=rdfxml $PROJECT_HOME/input/nibelungs.ttl

./rdfxml --out=ttl $PROJECT_HOME/input/nibelungs.ttl

./rdfxml --out=jsonld $PROJECT_HOME/input/nibelungs.ttl

./rdfxml --out=nq $PROJECT_HOME/input/nibelungs.ttl
