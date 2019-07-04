#!/bin/bash -v

docker run \
   -detach \
   -p 5432:5432 \
   -e POSTGRES_PASSWORD=********** \
   -v /pg/pgdata:/var/lib/postgresql/data \
   postgres:11.3

# Env:

# - POSTGRES_USER (default: )
# - POSTGRES_DB   (default: )
# - POSTGRES_INITDB_ARGS (ex: "--data-checksums")
# - POSTGRES_INITDB_WALDIR (ex: )
# - PGDATA (ex: )

