# Materialized views

## Create mv for geo table (country=US projection)
```
dht=> \d+ geoipcity
                                           Table "public.geoipcity"
   Column   |          Type          | Collation | Nullable | Default | Storage  | Stats target | Description 
------------+------------------------+-----------+----------+---------+----------+--------------+-------------
 startipnum | character varying(15)  |           | not null |         | extended |              | 
 endipnum   | character varying(15)  |           | not null |         | extended |              | 
 country    | character varying(255) |           | not null |         | extended |              | 
 region     | character varying(255) |           |          |         | extended |              | 
 city       | character varying(255) |           |          |         | extended |              | 
 postalcode | character varying(255) |           |          |         | extended |              | 
 lattitude  | double precision       |           |          |         | plain    |              | 
 longitude  | double precision       |           |          |         | plain    |              | 
 dmacode    | character varying(255) |           |          |         | extended |              | 
 areacode   | character varying(255) |           |          |         | extended |              | 
 startip    | bigint                 |           |          |         | plain    |              | 
 endip      | bigint                 |           |          |         | plain    |              | 
Indexes:
    "pk_geoipcity_ipnum" PRIMARY KEY, btree (startipnum, endipnum)
    "end_ip_idx" btree (endip)
    "start_ip_idx" btree (startip)
Check constraints:
    "correct_ip_range" CHECK (startip <= endip)
Access method: heap


dht=> create materialized view geoipcity_us as select * from geoipcity where country='US';
SELECT 2953913
```
analyze
```
dht=> select count(*),country from geoipcity group by country order by 1 desc limit 10;
  count  | country 
---------+---------
 2953913 | US
  334669 | GB
  321615 | IT
  243427 | DE
  176371 | FR
  175251 | NL
  149167 | CA
  136048 | JP
   92137 | AU
   84685 | CN
(10 rows)

dht=> select count(*) from geoipcity;
  count  
---------
 5748952
(1 row)


dht=> analyze verbose geoipcity_us;
INFO:  analyzing "public.geoipcity_us"
INFO:  "geoipcity_us": scanned 30000 of 44178 pages, containing 2005692 live rows and 0 dead rows; 30000 rows in sample, 2953582 estimated total rows
ANALYZE
dht=> 
dht=> analyze verbose geoipcity;
INFO:  analyzing "public.geoipcity"
INFO:  "geoipcity": scanned 30000 of 155757 pages, containing 1101632 live rows and 0 dead rows; 30000 rows in sample, 5719563 estimated total rows
ANALYZE
```