#!/bin/bash -ev

mvn flyway:migrate \
 -Dflyway.url=$PG_DEF_JDBC_URL \
 -Dflyway.user=$PG_DEF_JDBC_USER \
 -Dflyway.password=$PG_DEF_JDBC_PWD

