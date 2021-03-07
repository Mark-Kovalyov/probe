#!/bin/bash -v

docker run --detach \
       -p 9000:9000 \
       -e MINIO_ACCESS_KEY=AKIAIOSFODNN7EXAMPLE \
       -e MINIO_SECRET_KEY=**************************************** \
       -v /s3/data:/data \
       -v /s3/.minio:/root/.minio \
       minio/minio:RELEASE.2019-06-27T21-13-50Z server /data
