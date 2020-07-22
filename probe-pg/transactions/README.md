# Transactions

## Theory

|Isolation Level | Dirty Read           | Nonrepeatable Read | Phantom Read           | Serialization Anomaly |
|----------------|----------------------|--------------------|------------------------|-----------------------|
|Read uncommitted|Allowed, but not in PG| Possible           | Possible               | Possible              |
|Read committed  |Not possible          | Possible           | Possible               | Possible              |
|Repeatable read |Not possible          | Not possible       | Allowed, but not in PG | Possible              |
|Serializable    |Not possible          | Not possible       | Not possible           | Not possible          |


Railroad diagram
```
pg_begin_transaction ::= "BEGIN TRANSACTION" transaction_mode ? mode ? deferrable_option ?

transaction_mode ::= "ISOLATION LEVEL" ( "SERIALIZABLE" | "REPEATABLE READ" | "READ COMMITTED" | "READ UNCOMMITTED" )

mode ::= "READ WRITE" | "READ ONLY" 

deferrable_option ::=  "NOT" ? "DEFERRABLE" 
```

## Trivial
```
begin transaction;
commit;
```

## Savepoints

```
BEGIN;
 UPDATE ...
SAVEPOINT p1;
 UPDATE ...
ROLLBACK TO p1;
 UPDATE ...
COMMIT;
```

## Locking

```
LOCK TABLE tab1 ACCEESS EXCLUSIVE MODE;
```

Railroad diagram
```
lock_table ::= "LOCK" "TABLE" ? tablename "IN" lock_mode

lock_mode ::= ( ( "ROW" | "ACCESS" ) ? ( "SHARE" | "EXCLUSIVE" ) "MODE" | "SHARE ROW EXCLUSIVE MODE" )
```


```
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
```

