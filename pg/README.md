# Postgresql

## Base paths, variables

Ubuntu
```
/var/lib/postgresql/{version}/{pgservername}
```
Message log
```
/var/log/postgresql
```

## Detect instance

```
root@ $ service postgresql status
```

## Start/Restart/Shutdown

```
postgres@ $ ./pg_ctl stop
pg_ctl: no database directory specified and environment variable PGDATA unset
Try "pg_ctl --help" for more information.

postgres@ $ export PGDATA=/var/lib/postgresql/12/main
./pg_ctl stop
waiting for server to shut down.... done
server stopped
```

## Connect as superuser

Under postgres
```
sudo -u postgres bash
psql
```

## Show version
```
# select version();

PostgreSQL 12.5 (Ubuntu 12.5-0ubuntu0.20.04.1) 
on x86_64-pc-linux-gnu, compiled by gcc (Ubuntu 
9.3.0-17ubuntu1~20.04) 9.3.0, 64-bit
```

## Show data directory
```
# SHOW data_directory;
       data_directory        
-----------------------------
 /var/lib/postgresql/10/main
```

## Show databases

```
postgres=# \l
                                     List of databases
      Name    |  Owner   | Encoding |   Collate   |    Ctype    |   Access privileges   
   -----------+----------+----------+-------------+-------------+-----------------------
    johndb    | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =Tc/postgres         +
              |          |          |             |             | postgres=CTc/postgres+
              |          |          |             |             | john=CTc/postgres
    postgres  | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | 
    template0 | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +
              |          |          |             |             | postgres=CTc/postgres
    template1 | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +
              |          |          |             |             | postgres=CTc/postgres    
```

## Show accessable schemas

```
demo=> \dn
   List of schemas
   Name   |  Owner   
----------+----------
 bookings | postgres
 public   | postgres
(2 rows)

```

## Change search path for schema objects
```
SET search_path TO geoip, bookings, public;
```

## Show users

```
# \du+
                                          List of roles
 Role name |                         Attributes                         | Member of | Description 
-----------+------------------------------------------------------------+-----------+-------------
 john      |                                                            | {}        | 
 postgres  | Superuser, Create role, Create DB, Replication, Bypass RLS | {}        | 

```

## Show current user

```
postgres=# select current_user;
 current_user 
--------------
 postgres
(1 row)
```

## Show current schema

```
postgres=# select current_schema;
 current_schema 
----------------
 public
(1 row)
```

## Show tablespaces

```
postgres=# SELECT * FROM pg_tablespace;
  spcname   | spcowner | spcacl | spcoptions 
------------+----------+--------+------------
 pg_default |       10 |        | 
 pg_global  |       10 |        | 
 geospace   |    16385 |        | 
(3 rows)
```

```
postgres=# \db+
                                    List of tablespaces
    Name    |  Owner   |   Location   | Access privileges | Options |  Size  | Description 
------------+----------+--------------+-------------------+---------+--------+-------------
 geospace   | john     | /pg/geospace |                   |         | 458 MB | 
 pg_default | postgres |              |                   |         | 705 MB | 
 pg_global  | postgres |              |                   |         | 574 kB | 
(3 rows)
```

### File attributes (drwx------)

Under postgresql
```
postgres@: $ ls -lF /bigdata/pg/
total 0
drwx------ 1 postgres postgres 30 Aug 16 21:40 dht/

postgres@: $ chmod -R g+rx /bigdata/pg
drwxr-x--- 1 postgres postgres 30 Aug 16 21:40 dht/
```
Under root
```
root@: $ usermod -a -G postgres,ssl-cert mayton
```

## Change password
```
ALTER USER john WITH PASSWORD 'pwd123';
```

## Create tablespace
```
CREATE TABLESPACE axon_space OWNER mayton LOCATION '/bigdata/pg/axon';
```

## Create user
```
CREATE USER axon WITH PASSWORD 'pwd123';
```

## Create database with tablespace
```
CREATE DATABASE axondb OWNER axon TABLESPACE axon_space; 
```

## Allow user to create db
```
ALTER USER mayton CREATEDB;
```

## Connect via PSQL

```
$ psql -d johndb -U john -h localhost -p 5432
```

## Execute external SQL script (deploy new database)

```
$ psql -f ......
```

## Set new owner to database

```
$ ALTER DATABASE target_database OWNER TO new_onwer;
```

## Grant all privileges

```
$ GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO new_user;
```

## Demo/Booking
```
select * from bookings.*
```

## Grant privileges on schema
```
GRANT USAGE ON SCHEMA bookings TO new_user;
```

## Extract DDL

```
pg_dump -d dht -s -t person -f person.sql
```

## Show tables from specific schemas

```
\dt *.*
```

or 
```
\dt public.*
```
or
```
SHOW search_path;
SET search_path TO "$user", myschema, public;
```
