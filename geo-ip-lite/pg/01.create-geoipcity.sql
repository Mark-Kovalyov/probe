drop table geolite_ipv4;
drop table geolite_ipv6;
drop table geolite_loc;

-- ~ 121 297 rows
create table geolite_loc(
    geoname_id integer primary key,
    locale_code varchar(2),
    continent_code varchar(2),
    continent_name varchar(30), -- TODO:
    country_iso_code varchar(2),
    country_name varchar(60),
    subdivision_1_iso_code varchar(3),
    subdivision_1_name varchar(60),
    subdivision_2_iso_code varchar(3),
    subdivision_2_name varchar(40),
    city_name varchar(70), -- Instituto de Seguridad Social del Estado de Mexico y Municipios
    metro_code varchar(30),
    time_zone varchar(30),
    is_in_european_union integer -- {0|1}
) tablespace geospace;

-- ~ 3 115 535 rows
create table geolite_ipv4(
    network varchar(20) primary key,
    geoname_id integer,
    registered_country_geoname_id integer,
    represented_country_geoname_id integer,
    is_anonymous_proxy integer,
    is_satellite_provider integer,
    postal_code varchar(10),
    lattitude  double precision,
    longitude  double precision,
    accuracy_radius double precision
) tablespace geospace;

-- ~ 445 610 rows
-- 2001:0db8:11a3:09d7:1f34:8a2e:07a0:765d/128 - 43 chars
create table geolite_ipv6(
    network varchar(43) primary key,
    geoname_id integer,
    registered_country_geoname_id integer,
    represented_country_geoname_id integer,
    is_anonymous_proxy integer,
    is_satellite_provider integer,
    postal_code varchar(10),
    lattitude  double precision,
    longitude  double precision,
    accuracy_radius double precision
) tablespace geospace;

-- Load
\COPY geolite_loc    FROM /db/geoip.lite/GeoLite2-City-CSV_20191119/GeoLite2-City-Locations-en.csv CSV HEADER ENCODING 'utf-8';
\COPY geolite_ipv4   FROM /db/geoip.lite/GeoLite2-City-CSV_20191119/GeoLite2-City-Blocks-IPv4.csv  CSV HEADER ENCODING 'utf-8';
\COPY geolite_ipv6   FROM /db/geoip.lite/GeoLite2-City-CSV_20191119/GeoLite2-City-Blocks-IPv6.csv  CSV HEADER ENCODING 'utf-8';

-- Add constraints

CREATE INDEX idx_geolite_ipv4_fk ON geolite_ipv4(geoname_id) tablespace geospace;
ALTER TABLE geolite_ipv4 ADD CONSTRAINT geolite_ipv4_fk FOREIGN KEY(geoname_id) REFERENCES geolite_loc(geoname_id);

CREATE INDEX idx_geolite_ipv6_fk ON geolite_ipv6(geoname_id) tablespace geospace;
ALTER TABLE geolite_ipv6 ADD CONSTRAINT geolite_ipv6_fk FOREIGN KEY(geoname_id) REFERENCES geolite_loc(geoname_id);

analyze geolite_ipv4;
analyze geolite_ipv6;
analyze geolite_loc;





