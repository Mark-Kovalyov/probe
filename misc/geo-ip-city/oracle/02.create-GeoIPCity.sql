-- http://www.oracle.com/technetwork/database/enterprise-edition/databaseappdev-vm-161299.html?ssSourceSiteId=otnru
set echo on

set timing on

drop table geoipcity;

create table geoipcity(
	startIpNum VARCHAR2(15)  not null,
	endIpNum   VARCHAR2(15)  not null,
	country    VARCHAR2(255) not null,
	region     VARCHAR2(255),
	city       VARCHAR2(255),
	postalCode VARCHAR2(255),
	latitude   binary_double,
	longitude  binary_double,
	dmaCode    VARCHAR2(255),
	areaCode   VARCHAR2(255)
) PCTFREE 99 ROWDEPENDENCIES;
 

exit
