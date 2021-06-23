# DBA tools

## Performance
Wal
```
alter system set max_wal_size = '4GB';
alter system set min_wal_size = '1GB';
```
Buffer pool (AKA shared_buffers)
```
alter system set shared_buffers = '1G'
```
TODO: Check : buffers count = 1G / 8192 = shared_buffers / block_size

## Monitoring
```
show track_io_timing;
 track_io_timing 
-----------------
 off
(1 row)

```


## Select and kill session

Select
```
# select pid,usename,query,state,query_start from pg_stat_activity where state = 'active';
  pid  | usename  |                                          query                                           | state  |          query_start          
-------+----------+------------------------------------------------------------------------------------------+--------+-------------------------------
 51206 | postgres | select pid,usename,query,state,query_start from pg_stat_activity where state = 'active'; | active | 2020-11-19 22:36:04.432145+02
 60395 | username | delete from person where id in (select id from person_tmp);                              | active | 2020-11-19 22:33:35.958549+02
(2 rows)
```
Kill
```
# SELECT pg_cancel_backend(60395);
 pg_cancel_backend 
-------------------
 t
(1 row)

```

## Analyze plan

```
explain ...
```

## Drop all schema table

```
DO $$ DECLARE
    r RECORD;
BEGIN
    FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = current_schema()) LOOP
        EXECUTE 'DROP TABLE IF EXISTS ' || quote_ident(r.tablename) || ' CASCADE';
    END LOOP;
END $$;
```

## Top 5 data-table by disk size
```
 SELECT nspname || '.' || relname AS "relation",
     pg_size_pretty(pg_relation_size(C.oid)) AS "size"
   FROM pg_class C
   LEFT JOIN pg_namespace N ON (N.oid = C.relnamespace)
   WHERE nspname NOT IN ('pg_catalog', 'information_schema')
   ORDER BY pg_relation_size(C.oid) DESC
   LIMIT 5;
         relation          |  size   
---------------------------+---------
 public.geoipcity          | 1217 MB
 public.pk_geoipcity_ipnum | 576 MB
 public.geoipcity_us       | 345 MB
 public.start_ip_idx       | 123 MB
 public.end_ip_idx         | 123 MB
(5 rows)

```
