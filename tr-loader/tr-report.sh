#!/bin/bash -v

rm db-report.log

for i in assetclass organization person quote currency
do
 psql -e -d dht -c "select count(*) as cnt from $i" | tee -a db-report.log
done;
