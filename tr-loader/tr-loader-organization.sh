#!/bin/bash -v

./tr-loader \
 --source /db/db/TR/open-perm-id/OpenPermID-bulk-organization-20181223_053449.ttl.gz \
 --dest /storage/tr-loader/organization.sql \
 --tablename organization

psql -d dht -a -f sql/ddl/organizations.sql

psql -d dht -f /storage/tr-loader/organization.sql 2>&1 > /dev/null

psql -d dht -c 'select count(*) as cnt from organization;'


