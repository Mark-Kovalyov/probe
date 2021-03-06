drop table geoipcity;

create table geoipcity(
    startIpNum varchar(15) not null,
    endIpNum   varchar(15) not null,
    country    varchar(255) not null,
    region     varchar(255),
    city       varchar(255),
    postalCode varchar(255),
    lattitude  double precision,
    longitude  double precision,
    dmaCode    varchar(255),
    areaCode   varchar(255)
) without oids;




-- Load
\COPY geoipcity FROM /db/geoip/03.GeoIPCity.csv CSV HEADER ENCODING 'win-1251'; 

-- Clean empty strings
update geoipcity set postalcode=null where postalcode='';
update geoipcity set region=null where region='';
vacuum;


create unique index idx_geoipcity_ipnum on geoipcity(startip,endip);

alter table geoipcity add constraint pk_geoipcity_ipnum primary key using index idx_geoipcity_ipnum;

drop function ip2num;

-- in - IP address in decimal format with dots. Ex: '127.0.0.1'
-- out - biginteger, that represents IP address.
create function ip2num(ip varchar) returns bigint as $$
declare 
  w text; 
  x text;
  y text;
  z text; 
  l   integer;
  cp  integer;
  cp2 integer; 
begin

  l := char_length(ip);
  if l > 15 then 
    return null;
  end if;  
  if l < 7 then 
    return null;
  end if;
  
  -- 192.168.1.1

  cp  := 1; 
  cp2 := position('.' in ip); -- 4
  l   := cp2 - cp; -- 3
  w   := substring(ip from cp for l); -- '192'

  cp  := cp2 + 1; -- 5
  cp2 := cp  + position('.' in substring(ip from cp)) - 1; -- 8
  l   := cp2 - cp; -- 8-5=3
  x   := substring(ip from cp for l); -- 168
  
  cp  := cp2 + 1; 
  cp2 := cp  + position('.' in substring(ip from cp)) - 1; 
  l   := cp2 - cp; 
  y   := substring(ip from cp for l); 

  z   := substring(ip from cp2 + 1);

  return 16777216 * cast(w as bigint) + 
            65536 * cast(x as bigint) + 
              256 * cast(y as bigint) + 
                    cast(z as bigint);
end;
$$ language plpgsql immutable;

drop function num2ip;

create function num2ip(num bigint) returns text as $$
declare
  ip text;
begin
  ip:='';
  ip:=ip||cast((num / 16777216 % 256) as text);
  ip:=ip||'.';
  ip:=ip||cast((num / 65536 % 256) as text);      
  ip:=ip||'.';
  ip:=ip||cast((num / 256 % 256) as text);
  ip:=ip||'.';
  ip:=ip||cast((num % 256) as text);
  return ip;
end;
$$ language plpgsql immutable;

select ip2num('192.168.0.1');

drop view vgeoipcity;

create view vgeoipcity as
select 
 ip2num(startIpNum)                    as startip,
 ip2num(endIpNum)                      as endip,
 ip2num(endIpNum) - ip2num(startIpNum) as blocksize,
 startIpNum      as startIpNum,
 endIpNum        as endIpNum,
 country         as country,  
 region          as region,
 city            as city,
 postalcode      as postalcode,
 lattitude       as lattitude,
 longitude       as longitude,
 dmacode         as dmacode,
 areacode        as areacode 
from 
 geoipcity;

# psql -c '\ COPY geoipcuty FROM /db/geoip/03.GeoIPCity.csv CSV HEADER QUOTE '"'   ';

analyze;


alter table geoipcity add column startIp bigint;
alter table geoipcity add column endIp   bigint;
alter table geoipcity add constraint correct_ip_range check (startIp <= endIp);

update geoipcity2 set startIp = ip2num(startIpNum), endIp = ip2num(endIpNum);

create index ip_range on geoipcity(startIp,endIp) tablespace dhtspace;

create or replace function detectgeoobject(num bigint) returns text as $$
declare
  geoobject text;
begin
  select
    country ||' '||region||'/'||city||' ('||lattitude||','||longitude||')'
  into
    geoobject
  from
    geoipcity
  where
    startip <= num and num < endip;
end;
$$ language plpgsql immutable;

DROP TYPE geoobject_type;

CREATE TYPE geoobject_type AS (startipnum text, endipnum text, country text, region text, city text, lattitude real, longitude real);

create or replace function detectgeoobject(num bigint) returns geoobject_type as $$
declare
  geoobject geoobject_type;
  cnt integer;
begin
  select
    startipnum , endipnum , country , region , city , lattitude , longitude
  into
    geoobject
  from
    geoipcity
  where
    startip <= num and num < endip;
  if not found then
    return null;
  else
    return geoobject;
  end if;
end;
$$ language plpgsql immutable;


dht=> prepare q1(bigint) as select * from geoipcity where $1 > startIp and $1 < endIp;

dht=> explain execute q1(100000);
                                 QUERY PLAN                                  
-----------------------------------------------------------------------------
 Index Scan using ip_range on geoipcity  (cost=0.43..7.36 rows=1 width=88)
   Index Cond: ((startip < '100000'::bigint) AND (endip > '100000'::bigint))
(2 rows)

dht=> drop index ip_range;
DROP INDEX

dht=> create index start_ip_idx on geoipcity(startip);
CREATE INDEX

dht=> explain execute q2(2000);
                                  QUERY PLAN                                   
-------------------------------------------------------------------------------
 Index Scan using start_ip_idx on geoipcity  (cost=0.43..6.60 rows=1 width=88)
   Index Cond: (startip < '2000'::bigint)
   Filter: ('2000'::bigint < endip)
(3 rows)

dht=> create index end_ip_idx on geoipcity(endip);
CREATE INDEX

dht=> prepare q3(bigint) as select * from geoipcity where $1 > startIp and $1 < endIp;
PREPARE
dht=> explain execute q3(2000);
                                  QUERY PLAN                                   
-------------------------------------------------------------------------------
 Index Scan using start_ip_idx on geoipcity  (cost=0.43..6.61 rows=1 width=88)
   Index Cond: (startip < '2000'::bigint)
   Filter: ('2000'::bigint < endip)
(3 rows)
