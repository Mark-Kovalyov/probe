# Cassandra Day Russia Workshop 2

* Создание эффективной модели данных для высоконагруженных приложений: Cassandra Day Russia WS II https://www.youtube.com/watch?v=5U_mbXq61rk
* https://community.datastax.com

## Case 1

R1: Find comments related to target video using its identifier
* Get most recent first
* Pagination

R2: Find comments related to target user using its identifier
* Get most recent first
* Pagination

R3: Implement CRUD operations

```
create table comments_by_video(
   userid  
   creationdate
   commentid
   videoid
   .....
```

Q: What is the benefit of 2-tables usage?
A: Get rid of full-cluster scan

We're unable to know userId and videoId both! 

### Partition mustn't be huge

* Partitions stores as solid block in filesystem.
* Hotspots. SSTable-Compaction will affect at least 3 nodes (because of replication factor=3)

## Case 2

"country" as partition key is not a good choice 

## Case 3

Every 10 seconds sensor reports dimensions : { sensorId, timestamp, country, room, value }
How to define data model?

```
create table sensor_reports(
  sensorId  uuid,
  timestamp timestamp
  country   text,
  room      integer,
  primary key ((uuid), timestamp)
);
```

In long time-perspective there is possible cumulative grows of partition size. All partitions!

Solution: add new "syntetic key" month+year

```
create table sensor_reports(
  sensorId   uuid,
  month_year text,
  timestamp timestamp
  country   text,
  room      integer,
  primary key ((uuid, month_year), timestamp)
);
```

Compaction strategy optimized for "time-series" also supported.

## Case 4

### TimeUUID - UUID with time.

* Like Id
* Able to sort by UUID

The same example with user+videos with timeuuid:
```
create table comments_by_user(
 userid uuid,
 commentid timeuuid,
 videoid uuid,
 comment text
 primary key ((userid), commentid)
) with clustering order by (commentid DESC);

create table comments_by_video(
  ...
) with clustering order by (commentid DESC);
```

## Lessons Learned

* Keyspace - contains tables
* Table - contains partitions
* Row has a PK + data columns
* Partition - basic unit of storage/retrival
  * Identified by partition key embedded within primary key
  * Contains one or more rows
* Primary key - intra-table row identifier
  * Consists of partition key and clustering columns
  * Partition key - partition identifier, hashes to partition token
  * Clustering column - intra-partition key for sorting rows within partitions  

## Update == Insert