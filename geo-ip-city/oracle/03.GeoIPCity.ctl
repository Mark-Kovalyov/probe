load data
 infile '03.GeoIPCity.csv'
 truncate into table geoipcity
 fields terminated by ',' optionally enclosed by '"'		  
 TRAILING NULLCOLS
 ( 
	startIpNum,
	endIpNum  ,
	country   ,
	region    ,
	city      ,
	postalCode,
        latitude  ,
	longitude ,
	dmaCode   ,
	areaCode 
 )