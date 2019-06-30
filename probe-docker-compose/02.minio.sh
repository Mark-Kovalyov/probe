#!/bin/bash -v

docker run --detach \
       -p 9000:9000 \
       -e MINIO_ACCESS_KEY=AKIAIOSFODNN7EXAMPLE \
       -e MINIO_SECRET_KEY=**************************************** \
       -v /db/TR:/data \
       -v /db/TR/.minio:/root/.minio \
       minio/minio server /data
