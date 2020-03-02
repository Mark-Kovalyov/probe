# Table compression

## Oracle

Oracle Advanced Compression (12c)

https://www.oracle.com/technetwork/database/options/compression/advanced-compression-wp-12c-1896128.pdf

Oracle Advanced Compression Whitepaper (11g)

https://www.oracle.com/technetwork/database/advanced-compression-whitepaper-130502.pdf

Oracle Advanced Compression FAQ

https://www.oracle.com/ocom/groups/public/@otn/documents/webcontent/3861046.pdf

## MSSQL

Page Compression Implementation 

https://docs.microsoft.com/en-us/sql/relational-databases/data-compression/page-compression-implementation?view=sql-server-ver15

### Table

```
 USE AdventureWorks2012;  
 GO  
 EXEC sp_estimate_data_compression_savings 'Production', 'TransactionHistory', NULL, NULL, 'ROW' ;  

 ALTER TABLE Production.TransactionHistory REBUILD PARTITION = ALL  
 WITH (DATA_COMPRESSION = ROW);   
 GO  
```

### Index

```
 USE AdventureWorks2012;   
 GO  
 SELECT name, index_id  
 FROM sys.indexes  
 WHERE OBJECT_NAME (object_id) = N'TransactionHistory';  

 EXEC sp_estimate_data_compression_savings   
    @schema_name = 'Production',   
    @object_name = 'TransactionHistory',  
    @index_id = 2,   
    @partition_number = NULL,   
    @data_compression = 'PAGE' ;   

 ALTER INDEX IX_TransactionHistory_ProductID ON Production.TransactionHistory REBUILD PARTITION = ALL WITH (DATA_COMPRESSION = PAGE);  
 GO  
```

## MySQL 8.0 (InnoDB engine)

https://dev.mysql.com/doc/refman/8.0/en/innodb-compression-usage.html

### In File-Per-Table Tablespace

```
 SET GLOBAL innodb_file_per_table=1;
 CREATE TABLE t1
  (c1 INT PRIMARY KEY)
  ROW_FORMAT=COMPRESSED
 KEY_BLOCK_SIZE=8;
```

### In general tablespace

```
 mysql> CREATE TABLESPACE `ts2` ADD DATAFILE 'ts2.ibd' FILE_BLOCK_SIZE = 8192 Engine=InnoDB;

 mysql> CREATE TABLE t4 (c1 INT PRIMARY KEY) TABLESPACE ts2 ROW_FORMAT=COMPRESSED KEY_BLOCK_SIZE=8;
```

## MySQL 8.0 (MyISAM)

https://dev.mysql.com/doc/refman/8.0/en/myisampack.html

