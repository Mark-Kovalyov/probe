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

-- ex:  8a248c70-756d-43fb-8ee6-023376cfe0e5 SHA1(jls14.pdf)= 6bef1fb31b119e582278d4425a0cad074fb9a1c4
--                                          SHA1(jvms14.pdf)= 60817a3d3b2b6cc4c0f97e6955af420203758a39
create table dht_table(hash_code, node);

-- ex:  8a248c70-756d-43fb-8ee6-023376cfe0e5, 188.225.125.8
--      67363b3f-958b-4beb-bd65-42323dffe933, 91.211.105.83
create table dht_nearest(node_id uuid primary key, ip inet);

create index dht_nearest_ip_idx on dht_nearest(ip);
