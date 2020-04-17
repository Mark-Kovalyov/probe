-- dht=# insert into dht_log values(now(), inet('127.0.0.1'));
--INSERT 0 1
--dht=# select * from dht_log;
--             dt             |    ip
------------------------------+-----------
-- 2020-04-17 20:02:55.171605 | 127.0.0.1

create table dht_log(dt timestamp not null, ip inet);

-- UPSERT

-- INSERT INTO dht_peer (ip, last_seen) VALUES (inet('127.0.0.1'), now()) ON CONFLICT (ip) DO UPDATE SET last_seen = now();

create table dht_peer(ip inet primary key, last_seen timestamp, reties integer);

