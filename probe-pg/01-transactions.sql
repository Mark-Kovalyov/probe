Isolation Level 	Dirty Read 		Nonrepeatable Read 	Phantom Read 		Serialization Anomaly
----------------------------------------------------------------------------------------------------
Read uncommitted 	Allowed, but not in PG 	Possible 		Possible 		Possible
Read committed 		Not possible 		Possible 		Possible 		Possible
Repeatable read 	Not possible 		Not possible 		Allowed, but not in PG 	Possible
Serializable 		Not possible 		Not possible 		Not possible 		Not possible


# SET TRANSACTION transaction_mode [, ...]
# SET TRANSACTION SNAPSHOT snapshot_id
# SET SESSION CHARACTERISTICS AS TRANSACTION transaction_mode [, ...]

# where transaction_mode is one of:

#    ISOLATION LEVEL { SERIALIZABLE | REPEATABLE READ | READ COMMITTED | READ UNCOMMITTED }
#    READ WRITE | READ ONLY
#    [ NOT ] DEFERRABLE


BEGIN TRANSACTION ISOLATION LEVEL REPEATABLE READ;
SELECT pg_export_snapshot();

BEGIN TRANSACTION ISOLATION LEVEL REPEATABLE READ;
SET TRANSACTION SNAPSHOT '00000003-0000001B-1';



BEGIN;
 UPDATE accounts SET balance = balance + 100.00 WHERE acctnum = 12345;
 UPDATE accounts SET balance = balance - 100.00 WHERE acctnum = 7534;
COMMIT;