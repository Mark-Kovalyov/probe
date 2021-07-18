package mayton.spark

case class GeoIpEntity(startIpNum : String,
                       endIpNum : String,
                       country : String,
                       region : String,
                       city : String,
                       postalCode : String,
                       latitude : String,
                       longitude : String,
                       dmaCode : String,
                       areaCode : String)
