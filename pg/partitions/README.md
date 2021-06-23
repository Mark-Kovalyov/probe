# Partitioning

## Links

* https://postgrespro.ru/docs/postgresql/12/ddl-partitioning
* https://www.postgresql.org/docs/12/ddl-partitioning.html

## Range Partitioning

```
CREATE TABLE measurement (
    city_id         int not null,
    logdate         date not null,
    peaktemp        int,
    unitsales       int
) PARTITION BY RANGE (logdate);

CREATE TABLE measurement_y2006m02 PARTITION OF measurement
    FOR VALUES FROM ('2006-02-01') TO ('2006-03-01');

CREATE TABLE measurement_y2007m12 PARTITION OF measurement
    FOR VALUES FROM ('2007-12-01') TO ('2008-01-01')
    TABLESPACE fasttablespace;

CREATE INDEX ON measurement (logdate);
```

## List Partitioning

```
create table sales (
    id         serial        ,
    product_id integer       NOT NULL,
    quantity   integer       CHECK (quantity > 0),
    price      numeric(38,2) CHECK (price > 0),
    region     VARCHAR(5)    CHECK (region in ('east','north','west','south'))
)
PARTITION BY LIST (region);

CREATE TABLE part_a PARTITION OF sales FOR VALUES IN ('east','north');
CREATE TABLE part_b PARTITION OF sales FOR VALUES IN ('west','south');
```

## Hash Partitioning

(since Postgresql 11.x)
```
CREATE TABLE hp ( foo text ) PARTITION BY HASH (foo);
CREATE TABLE hp_0 PARTITION OF hp FOR VALUES WITH (MODULUS 3, REMAINDER 0) TABLESPACE space_01;
CREATE TABLE hp_1 PARTITION OF hp FOR VALUES WITH (MODULUS 3, REMAINDER 1) TABLESPACE space_02;
CREATE TABLE hp_2 PARTITION OF hp FOR VALUES WITH (MODULUS 3, REMAINDER 2) TABLESPACE space_03;
```

## Show tablespace with location

```
select spcname,pg_tablespace_location(oid) from pg_tablespace;
```
