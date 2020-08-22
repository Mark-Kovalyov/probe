#!/bin/bash -v

OPERMID_HOME="/storage/db/TR/open-perm-id"
SQL_HOME="/storage/db/TR/open-perm-id"
TAB="instrument"
CNT="02"

mvn clean package

./tr-loader \
 --source  "$OPERMID_HOME/OpenPermID-bulk-instrument-20200809_084359.ttl.gz"  \
 --dest    "$SQL_HOME/$CNT.insert-$TAB.sql" \
 --ddldest "$SQL_HOME/$CNT.create-$TAB.sql" \
 --tablename "$TAB"

psql -d $DEMO_DB -a -f "$SQL_HOME/$CNT.create-$TAB.sql"

psql -d $DEMO_DB -a -f "$SQL_HOME/$CNT.insert-$TAB.sql" > /dev/null

psql -d $DEMO_DB -c "DELETE FROM $TAB WHERE id IN (SELECT id FROM (SELECT row_number() OVER (PARTITION BY id), id FROM $TAB) x WHERE x.row_number > 1)"

psql -d $DEMO_DB -c "analyze verbose $TAB"


