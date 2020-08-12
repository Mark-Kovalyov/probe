#!/bin/bash -v

mvn clean package

./tr-loader --source /db/db/TR/open-perm-id/OpenPermID-bulk-assetClass-20181230_053446.ttl.gz  --dest /storage/tr-loader/assetclass.sql  --tablename assetclass

psql -d dht -a -f sql/ddl/assetclass.sql

psql -d dht -a -f /storage/tr-loader/assetclass.sql

psql -d dht -c 'select count(*) from assetclass'


