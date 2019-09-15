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
) tablespace maytondb;

create unique index geoipcity_idx on geoipcity(startipnum,endipnum) tablespace maytondb;

alter table geoipcity add constraint geoipcity_pk primary key using index geoipcity_idx;
