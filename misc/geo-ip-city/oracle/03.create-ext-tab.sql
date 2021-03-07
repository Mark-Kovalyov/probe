create directory geoip_dir as 'c:\db\geoip';

CREATE TABLE geoipcity_ext 
ORGANIZATION EXTERNAL
(TYPE oracle_datapump DEFAULT DIRECTORY geoip_dir LOCATION ('geoipcity_ext.exp'))
AS
SELECT * FROM geoipcity;
