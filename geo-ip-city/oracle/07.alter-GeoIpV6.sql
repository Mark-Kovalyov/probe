SET ECHO ON

SET TIMING ON

SET FEEDBACK ON

SET AUTOTRACE ON

SET LINESIZE 200

SPOOL 07.alter-GeoIpV6.log

CREATE INDEX COUNTRY_CODE_IDX ON GeoIpV6(CountryCode);

CREATE TABLE Countries(
	CountryCode varchar2(2)   PRIMARY KEY,
	Country     varchar2(255) NOT NULL
) ORGANIZATION INDEX;

INSERT /*+ APPEND */ INTO COUNTRIES SELECT DISTINCT CountryCode,Country FROM geoipv6;
COMMIT;

ALTER TABLE GeoIpV6 
      ADD CONSTRAINT fk_country_code 
      FOREIGN KEY (CountryCode) 
      REFERENCES Countries(CountryCode);

ALTER TABLE GeoIpV6 SET UNUSED (Country); 

CREATE TABLE Cities(
	CityID number PRIMARY KEY,
	City   varchar2(255) NOT NULL
) ORGANIZATION INDEX;

CREATE SEQUENCE CitySeq;

INSERT /*+ APPEND */ INTO Cities(CityID,City) 
    SELECT CitySeq.nextval,City from (
        SELECT DISTINCT City FROM GeoIpCity where City is not null);

COMMIT;

ALTER TABLE GeoIPCity ADD CityID number;

UPDATE GeoIPCity NOLOGGING set CityID = (SELECT CityID from Cities c where c.CityID=CityID and rownum=1);
COMMIT;

ALTER TABLE GeoIPCity SET UNUSED (City);

EXEC DBMS_STATS.GATHER_TABLE_STATS(user,'COUNTRIES',cascade => true);

EXEC DBMS_STATS.GATHER_TABLE_STATS(user,'GEOIPV6',cascade => true);

EXEC DBMS_STATS.GATHER_TABLE_STATS(user,'CITIES',cascade => true);

-- EXEC DBMS_STATS.GATHER_TABLE_STATS(user,'GEOIPCITY',cascade => true);

SET AUTOTRACE OFF
