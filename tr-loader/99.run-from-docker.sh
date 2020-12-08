#!/bin/bash -v

OPERMID_HOME="/storage/db/TR/open-perm-id"
SQL_HOME="/storage/db/TR/open-perm-id"
TAB="currency"
CNT="00"

docker run tr-loader \
 -p 8080 8080 \
 --source  "$OPERMID_HOME/OpenPermID-bulk-currency-20200809_084354.ttl.gz"  \
 --destfolder "$SQL_HOME" \
 --destddl    "$CNT.insert-$TAB.sql" \
 --destdml    "$CNT.create-$TAB.sql" \
 --tablename  "$TAB"

<< 'TODO'
[INFO ]  : :: Estimation
[DEBUG]  : GZIPInputStream detected
Exception in thread "main" java.io.FileNotFoundException: /storage/db/TR/open-perm-id/OpenPermID-bulk-currency-20200809_084354.ttl.gz (No such file or directory)
	at java.base/java.io.FileInputStream.open0(Native Method)
	at java.base/java.io.FileInputStream.open(FileInputStream.java:219)
	at java.base/java.io.FileInputStream.<init>(FileInputStream.java:157)
	at java.base/java.io.FileInputStream.<init>(FileInputStream.java:112)
	at mayton.semanticweb.TRLoader.autoDetectCompressedStream(TRLoader.java:89)
	at mayton.semanticweb.TRLoader.countOrGetStatements(TRLoader.java:44)
	at mayton.semanticweb.TRLoader.processFile(TRLoader.java:121)
	at mayton.semanticweb.TRLoader.main(TRLoader.java:218)
TODO