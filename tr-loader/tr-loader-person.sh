#!/bin/bash -v

./tr-loader --source /db/db/TR/open-perm-id/OpenPermID-bulk-person-20181230_060310.ttl.gz --dest /storage/tr-loader/person.sql --tablename person

psql -d dht -a -f sql/ddl/person.sql

psql -d dht -f /storage/tr-loader/person.sql 2>&1 > /dev/null

psql -d dht -c 'select count(*) as cnt from person;'


