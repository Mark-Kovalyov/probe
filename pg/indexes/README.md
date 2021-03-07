# Indexes

## Hash


## Gist


## GIN


## SP-Gist


## Brin

CREATE INDEX idx_brin ON t_test USING brin(id);


## Bloom

$ apt-get install postgresql-contrib
Reading package lists... Done
Building dependency tree       
Reading state information... Done
postgresql-contrib is already the newest version (10+190).
0 upgraded, 0 newly installed, 0 to remove and 3 not upgraded.

Under super user 'postgres'

postgres=# create extension bloom;
CREATE EXTENSION
postgres=# 
postgres=# create extension bloom;
ERROR:  extension "bloom" already exists

Under user

CREATE TABLE t_bloom (x1 int, x2 int, x3 int, x4 int, x5 int, x6 int, x7 int);

CREATE INDEX idx_bloom ON t_bloom USING bloom(x1, x2, x3, x4, x5, x6, x7);


