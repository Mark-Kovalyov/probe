# Postgresql

## Detect instance

```
service postgresql status
```

## Show data directory

Under postgres
```
sudo -u postgres bash
psql
# SHOW data_directory;
       data_directory        
-----------------------------
 /var/lib/postgresql/10/main
```

## Show databases

```postgres=# \l
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

## Show users

```
# \du+
                                          List of roles
 Role name |                         Attributes                         | Member of | Description 
-----------+------------------------------------------------------------+-----------+-------------
 john      |                                                            | {}        | 
 postgres  | Superuser, Create role, Create DB, Replication, Bypass RLS | {}        | 

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
```
total 0
drwx------ 1 postgres postgres 30 Nov 27 00:53 geospace/
```

## Change password
```
ALTER USER john WITH PASSWORD 'pwd123';
```

## Create tablespace
```
CREATE TABLESPACE geospace OWNER john LOCATION '/pg/geospace';
```

## Connect via PSQL

```
$ psql -d johndb -U john -h localhost -p 5432
```