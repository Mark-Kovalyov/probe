# AVRO

https://avro.apache.org/

## GeoIpAvro

* Binary and JSON
* Schema in header
* Splittable: YES
* Row-like
* Not null fields
* Integer == varint (zigzag)
* Enumerations
* Complex types: records
* Unions ?

### Required Codecs

* The "null" codec simply passes through data uncompressed.
* The "deflate" codec writes the data block using the deflate algorithm as specified in RFC 1951, and typically implemented using the zlib library. Note that this format (unlike the "zlib format" in RFC 1950) does not have a checksum.

### Optional Codecs

* The "bzip2" codec uses the bzip2 compression library.
* The "snappy" codec uses Google's Snappy compression library. Each compressed block is followed by the 4-byte, big-endian CRC32 checksum of the uncompressed data in the block. 
* The "xz" codec uses the XZ compression library.
* The "zstandard" codec uses Facebook's Zstandard compression library.

### Encoders (Java Implementation)
* 

Cons:
* External Schema required ?

### Statistics

|Source(CSV)|Dest(AVRO)|Ratio|Encode Time|Rows   |Speed          |Compression Codec|
|-----------|----------|-----|-----------|-------|---------------|-----------------|
|TODO

Sample:
```
startIpNum,endIpNum,country,region,city,postalCode,latitude,longitude,dmaCode,areaCode
1.0.0.0,1.7.255.255,"AU","","","",-27.0000,133.0000,,
1.9.0.0,1.9.255.255,"MY","","","",2.5000,112.5000,,
...
1.23.32.0,1.23.63.255,"IN","02","Hyderabad","",17.3753,78.4744,,
```

Format
```
TODO
```

## Links 

* Avro4s https://github.com/sksamuel/avro4s

## Quotations

*

_Avro includes a simple object container file format. A file has a schema,
and all objects stored in the file must be written according to that schema,
using binary encoding. Objects are stored in blocks that may be compressed.
Syncronization markers are used between blocks to permit efficient splitting
of files for MapReduce processing._

*

_If your use case typically scans or retrieves all of the fields 
in a row in each query, Avro is usually the best choice. Avro was 
designed to remove all short-comings in Sequence files and this 
is recommended format for Hadoop. Avro has a external schema that 
the user defines and it is enforced on all the data in the file 
and it can be used in any language.It has very nice binary coding 
which makes the size even little smaller than protobuff._

_Avro is a row oriented format.Avro guarantees data is written to 
disk when flushed or synced with a very low memory overhead. Avro 
supports block compression and supports schema evolution._

## Known bugs

```
341947287 Jul 18 22:31 GeoIPCity-protobuf.dat
432144345 May 12  2019 GeoIPCity.utf-8.csv
161699011 Jul 18 22:38 GeoIPCity-v1-directBinaryEncoder.avro
303678770 Jul 18 22:38 GeoIPCity-v2-blockingBinaryEncoder.avro
```

```
$ avro-tools getschema GeoIPCity-v1-directBinaryEncoder.avro
Exception in thread "main" org.apache.avro.InvalidAvroMagicException: Not an Avro data file.
at org.apache.avro.file.DataFileStream.initialize(DataFileStream.java:111)
at org.apache.avro.file.DataFileReader.<init>(DataFileReader.java:139)
at org.apache.avro.file.DataFileReader.<init>(DataFileReader.java:131)
at org.apache.avro.tool.DataFileGetSchemaTool.run(DataFileGetSchemaTool.java:46)
at org.apache.avro.tool.Main.run(Main.java:67)
at org.apache.avro.tool.Main.main(Main.java:56)

$ avro-tools getschema GeoIPCity-v2-blockingBinaryEncoder.avro
Exception in thread "main" org.apache.avro.InvalidAvroMagicException: Not an Avro data file.
at org.apache.avro.file.DataFileStream.initialize(DataFileStream.java:111)
at org.apache.avro.file.DataFileReader.<init>(DataFileReader.java:139)
at org.apache.avro.file.DataFileReader.<init>(DataFileReader.java:131)
at org.apache.avro.tool.DataFileGetSchemaTool.run(DataFileGetSchemaTool.java:46)
at org.apache.avro.tool.Main.run(Main.java:67)
at org.apache.avro.tool.Main.main(Main.java:56)
```