# Apache ORC

## Stripe

https://cwiki.apache.org/confluence/display/Hive/LanguageManual+ORC#LanguageManualORC-StripeStructure

The default stripe size is 250 MB. Large stripe sizes enable large, efficient reads from HDFS.

https://aws.amazon.com/blogs/big-data/top-10-performance-tuning-tips-for-amazon-athena/

One parameter that could be tuned is the block size or stripe size. The block size in Parquet or 
stripe size in ORC represent the maximum number rows that can fit into one block in terms of size 
in bytes. The larger the block/stripe size, the more rows can be stored in each block. By default, 
the Parquet block size is 128 MB and the ORC stripe size is 64 MB. We recommend a larger 
block size if you have tables with many columns, to ensure that each column block remains 
at a size that allows for efficient sequential I/O.

```
Stripe
 +- IndexData (Column1, Column2,...)
 +- Row data (Col1,...)
 +- Stripe Footer
```

## TBLPROPERTIES (HiveQL)

* CREATE TABLE ... STORED AS ORC
* ALTER TABLE ... [PARTITION partition_spec] SET FILEFORMAT ORC
* SET hive.default.fileformat=Orc


|Key|Default|Notes|
|---|-------|-----|
|orc.compress|ZLIB|high level compression (one of NONE, ZLIB, SNAPPY)|
|orc.compress.size|256K|number of bytes in each compression chunk|
|orc.stripe.size|64M|number of bytes in each stripe|
|orc.row.index.stride|10,000|number of rows between index entries (must be >= 1000)|
|orc.create.index|true|whether to create row indexes|
|orc.bloom.filter.columns|""|comma separated list of column names for which bloom filter should be created|
|orc.bloom.filter.fpp|0.05|false positive probability for bloom filter (must >0.0 and <1.0)|


## Column Encoding

```
// the encoding is mapped directly to the stream using RLE v1
 DIRECT = 0;
 // the encoding uses a dictionary of unique values using RLE v1
 DICTIONARY = 1;
 // the encoding is direct using RLE v2
 DIRECT_V2 = 2;
 // the encoding is dictionary-based using RLE v2
 DICTIONARY_V2 = 3;
```

```
create table Addresses (
  name string,
  street string,
  city string,
  state string,
  zip int
) stored as orc tblproperties ("orc.compress"="NONE");
```


