mvn clean compile exec:java ^
 -Dfix.indexer.jdbc.url="jdbc:oracle:thin:scott/tiger@127.0.0.1:1521/XE" ^
 -Dfix.indexer.sql="fix.indexer.sql" ^
 -Dexec.mainClass="mayton.probe.FixIndexer" 

