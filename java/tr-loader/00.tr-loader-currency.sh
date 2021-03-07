#!/bin/bash -v

OPERMID_HOME="/storage/db/TR/open-perm-id"
SQL_HOME="/storage/db/TR/open-perm-id"
TAB="currency"
CNT="00"

mvn clean package

./tr-loader \
 --source  "$OPERMID_HOME/OpenPermID-bulk-currency-20200809_084354.ttl.gz"  \
 --destfolder "$SQL_HOME" \
 --destddl    "$CNT.insert-$TAB.sql" \
 --destdml    "$CNT.create-$TAB.sql" \
 --tablename  "$TAB"

psql -d $DEMO_DB -a -f "$SQL_HOME/00.create-$TAB.sql"

psql -d $DEMO_DB -a -f "$SQL_HOME/00.insert-$TAB.sql"

psql -d $DEMO_DB -c "analyze verbose $TAB"


