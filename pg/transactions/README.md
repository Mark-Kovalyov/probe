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
LOCK TABLE tab1 IN EXCLUSIVE MODE;
```

## System columns

* oid
* tableoid
* xmin
* cmin
* xmax
* cmax
* ctid

```
dht=> select tableoid, xmin, cmin, xmax, cmax, ctid from person limit 10;
 tableoid |  xmin   |   cmin   |  xmax   |   cmax   |    ctid     
----------+---------+----------+---------+----------+-------------
    33252 | 4749767 |        0 | 4749951 |        0 | (307632,1)
    33252 | 4749767 | 13101907 |       0 | 13101907 | (307632,2)
    33252 | 4749767 |        0 | 4749951 |        0 | (307632,3)
    33252 | 4749767 | 13101909 |       0 | 13101909 | (307632,4)
    33252 | 4749767 |        0 | 4749951 |        0 | (307632,5)
    33252 | 4749767 | 13101911 |       0 | 13101911 | (307632,6)
    33252 | 4749767 |        0 | 4749951 |        0 | (307632,7)
    33252 | 4749767 | 13101913 |       0 | 13101913 | (307632,8)
    33252 | 4749767 |        0 | 4749951 |        0 | (307632,9)
    33252 | 4749767 | 13101915 |       0 | 13101915 | (307632,10)
(10 rows)
```

