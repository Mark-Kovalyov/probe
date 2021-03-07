#!/bin/bash -v

curl -X POST -d '{"gremlin":"g.V().limit(1)"}' http://your-neptune-endpoint:8182/gremlin

curl -G "http://your-neptune-endpoint:8182?gremlin=g.V().count()"