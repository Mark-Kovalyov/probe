#!/bin/bash -v

mvn clean package

./tr-loader --source /db/db/TR/open-perm-id/OpenPermID-bulk-quote-20181230_054022.ttl.gz  --dest /storage/tr-loader/quote.sql  --tablename quote

psql -d dht -a -f sql/ddl/quote.sql

psql -d dht -a -f /storage/tr-loader/quote.sql 2>&1 > /dev/null



