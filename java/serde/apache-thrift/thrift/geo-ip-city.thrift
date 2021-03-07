namespace java mayton.geo.generated

enum Country {
 EN,
 FR,
 GE
}

struct GeoIpCity {
  1 : required i32 startIpNum,
  2 : required i32 endIpNum,
  3 : optional Country country,
  4 : optional string region,
  5 : optional string city,
  6 : optional string postalCode,
  7 : required double latitude,
  8 : required double longitude,
  9 : optional string dmaCode,
 10 : optional string areaCode
}

service GeoIpService {
    void store(1 : GeoIpCity geoIpCity)
    GeoIpCity retrieve(1 : i32 num)
}
