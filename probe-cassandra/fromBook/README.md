# Tutorial from "Apache Cassandra" - Jeff Carpenter, Eben Hewitt

## Hotel

```
create keyspace hotel with replication = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

create type hotel.address (
 street text,
 state text,
 postalcode text,
 country text
);

create table hotel.hotels_by_poi(
 poi_name text,
 hotel_id text,
 name text,
 phone text,
 address frozen<address>,
 primary key ((poi_name), hotel_id)
) with clustering order by (hotel_id asc);

create 
```

