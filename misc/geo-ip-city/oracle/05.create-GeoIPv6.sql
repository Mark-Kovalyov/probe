set echo on

set timing on

SPOOL 05.create-GeoIpV6.log

drop table geoipv6;

create TABLE geoipv6(
	StartIp     varchar2(39) not null,
	EndIp       varchar2(39) not null,
	StartIpNum  number(38) not null,
	EndIpNum    number(38) not null,
	CountryCode varchar2(255),
	Country     varchar2(255)
) pctfree 10
ROWDEPENDENCIES ;

CREATE UNIQUE INDEX GEOIPV6_PK_INDX ON GEOIPV6(STARTIPNUM,ENDIPNUM);

ALTER TABLE GeoIpV6 ADD CONSTRAINT pk_geoipv6 PRIMARY KEY (startIpNum,endIpNum) USING INDEX;

CREATE INDEX N_STARTIP6_INDEX ON GEOIPV6(StartIpNum);

CREATE INDEX N_ENDIP6_INDEX   ON GEOIPV6(EndIpNum);

EXEC DBMS_STATS.GATHER_TABLE_STATS(user,'GEOIPV6',cascade => true);

SPOOL OFF

exit
