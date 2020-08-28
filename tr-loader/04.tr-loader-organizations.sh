#!/bin/bash -v

OPERMID_HOME="/storage/db/TR/open-perm-id"
SQL_HOME="/storage/db/TR/open-perm-id"
TAB="organization"
CNT="04"

mvn clean package

if [ -f "$SQL_HOME/$CNT.insert-$TAB.sql" ]; then
 echo "Skipping generate SQL."
else
./tr-loader \
 --source     "$OPERMID_HOME/OpenPermID-bulk-organization-20200816_084422.ttl.gz" \
 --destfolder "$SQL_HOME" \
 --destdml    "$CNT.insert-$TAB.sql" \
 --destddl    "$CNT.create-$TAB.sql" \
 --tablename  "$TAB" 
fi

psql -d $DEMO_DB -a -f "$SQL_HOME/$CNT.create-$TAB.sql"

psql -d $DEMO_DB -b -f "$SQL_HOME/$CNT.insert-$TAB.sql" > /dev/null

psql -d $DEMO_DB -c "DELETE FROM $TAB WHERE id IN (SELECT id FROM (SELECT row_number() OVER (PARTITION BY id), id FROM $TAB) x WHERE x.row_number > 1)"

psql -d $DEMO_DB -c "CREATE UNIQUE INDEX organization_pk ON $TAB(id) tablespace dhtspace"

psql -d $DEMO_DB -c "analyze verbose $TAB"


