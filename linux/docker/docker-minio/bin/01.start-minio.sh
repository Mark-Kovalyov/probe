#!/bin/bash -v

docker run --restart=no --detach -p 9000:9000 minio/minio server /db/TR
