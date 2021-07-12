# Hadoop

## Terminology

* Mapper
* Reducer
* Partitioner (HashPartitioner by default)
* Reporter
* OutputCollector

## Filesystems

* Local : "file://home/file.gz"
* Amazon S3 : "s3://bucket/myweaher/16-01.gz"
* HDFS : "hdfs://master:port/path"

## File Formats

* CSV
* JSON
* Sequence Files
* AVRO
* Apache Thrift  
* Protobuf
* Parquet (Columnar, based on Thrift)
* RCFile(Record Columnar File)
* Apache ORC (Columnar, based on Protobuf)

## Splittable hadoop file formats 

* AVRO
    * http://apache-avro.679487.n3.nabble.com/Is-Avro-Splittable-td4032414.html
* Apache ORC 
    * https://cwiki.apache.org/confluence/display/hive/languagemanual+orc
* Sequence Files(TODO: prof?) 
    * https://stackoverflow.com/questions/8405671/splitting-sequencefile-in-controlled-manner-hadoop/8406902

## Splittable compression formats

* BZip2
  * https://stackoverflow.com/questions/14820450/best-splittable-compression-for-hadoop-input-bz2
* LZ4 (?) 
  * https://stackoverflow.com/questions/54906265/lz4-compression-is-not-splittable
* LZO 
  * https://cloudera.ericlin.me/2016/09/how-to-index-lzo-files-in-hadoop/
  * https://pypi.org/project/lzo-indexer/  
    
### LZ4

* https://commons.apache.org/proper/commons-compress/javadocs/api-release/org/apache/commons/compress/compressors/lz4/package-summary.html
* https://github.com/lz4/lz4-java

## Bzip2 CLI

```bash
bzip2, a block-sorting file compressor.  Version 1.0.8, 13-Jul-2019.

   usage: bzip2 [flags and input files in any order]

   -h --help           print this message
   -d --decompress     force decompression
   -z --compress       force compression
   -k --keep           keep (don't delete) input files
   -f --force          overwrite existing output files
   -t --test           test compressed file integrity
   -c --stdout         output to standard out
   -q --quiet          suppress noncritical error messages
   -v --verbose        be verbose (a 2nd -v gives more)
   -L --license        display software version & license
   -V --version        display software version & license
   -s --small          use less memory (at most 2500k)
   -1 .. -9            set block size to 100k .. 900k
   --fast              alias for -1
   --best              alias for -9

   If invoked as `bzip2', default action is to compress.
              as `bunzip2',  default action is to decompress.
              as `bzcat', default action is to decompress to stdout.

   If no file names are given, bzip2 compresses or decompresses
   from standard input to standard output.  You can combine
   short flags, so `-v -4' means the same as -v4 or -4v, &c.
```

## LZO CLI

* https://cloudera.ericlin.me/2016/09/how-to-index-lzo-files-in-hadoop/
```
hadoop jar /opt/cloudera/parcels/GPLEXTRAS-5.7.0-1.cdh5.7.0.p0.40/lib/hadoop/lib/hadoop-lzo.jar com.hadoop.compression.lzo.DistributedLzoIndexer /tmp/lzo_test
```

* https://pypi.org/project/lzo-indexer/

```bash
pip install lzo-indexer
```

CLI:

```bash
                          Lempel-Ziv-Oberhumer Packer
                           Copyright (C) 1996 - 2017
lzop v1.04         Markus Franz Xaver Johannes Oberhumer         Aug 10th 2017

Usage: lzop [-dxlthIVL19] [-qvcfFnNPkUp] [-o file] [-S suffix] [file..]

Commands:
  -1     compress faster                   -9    compress better
  -d     decompress                        -x    extract (same as -dPp)
  -l     list compressed file              -I    display system information
  -t     test compressed file              -V    display version number
  -h     give this help                    -L    display software license
Options:
  -q     be quiet                          -v       be verbose
  -c     write on standard output          -oFILE   write output to 'FILE'
  -p     write output to current dir       -pDIR    write to path 'DIR'
  -f     force overwrite of output files
  -n     do not restore the original file name (default)
  -N     restore the original file name
  -P     restore or save the original path and file name
  -S.suf use suffix .suf on compressed files
  -U     delete input files after successful operation (like gzip and bzip2)
  file.. files to (de)compress. If none given, try standard input.
```

## LZ4 CLI

* Block Independence?

```bash
*** LZ4 command line interface 64-bits v1.9.2, by Yann Collet ***
Usage : 
      lz4 [arg] [input] [output] 

input   : a filename 
          with no FILE, or when FILE is - or stdin, read standard input
Arguments : 
 -1     : Fast compression (default) 
 -9     : High compression 
 -d     : decompression (default for .lz4 extension)
 -z     : force compression 
 -D FILE: use FILE as dictionary 
 -f     : overwrite output without prompting 
 -k     : preserve source files(s)  (default) 
--rm    : remove source file(s) after successful de/compression 
 -h/-H  : display help/long help and exit 

Advanced arguments :
 -V     : display Version number and exit 
 -v     : verbose mode 
 -q     : suppress warnings; specify twice to suppress errors too
 -c     : force write to standard output, even if it is the console
 -t     : test compressed file integrity
 -m     : multiple input files (implies automatic output filenames)
 -r     : operate recursively on directories (sets also -m) 
 -l     : compress using Legacy format (Linux kernel compression)
 -B#    : cut file into blocks of size # bytes [32+] 
                     or predefined block size [4-7] (default: 7) 
 -BI    : Block Independence (default) 
 -BD    : Block dependency (improves compression ratio) 
 -BX    : enable block checksum (default:disabled) 
--no-frame-crc : disable stream checksum (default:enabled) 
--content-size : compressed frame includes original size (default:not present)
--list FILE : lists information about .lz4 files (useful for files compressed with --content-size flag)
--[no-]sparse  : sparse mode (default:enabled on file, disabled on stdout)
--favor-decSpeed: compressed files decompress faster, but are less compressed 
--fast[=#]: switch to ultra fast compression level (default: 1)
--best  : same as -12
Benchmark arguments : 
 -b#    : benchmark file(s), using # compression level (default : 1) 
 -e#    : test all compression levels from -bX to # (default : 1)
 -i#    : minimum evaluation time in seconds (default : 3s) 

```

## Other samples

LZ0
```bash
gunzip -c 1.2billion.txt.gz | pv -p --timer --rate --bytes --size 14388140441 | lzop -o /bigdata/billion/1.2billion.txt.lzo
```

Bzip2
```bash
gunzip -c 1.2billion.txt.gz | pv -p --timer --rate --bytes | bzip2 -z -c --fast > /bigdata/billion/1.2billion.txt.bz2
```

LZ4
```bash
gunzip -c 1.2billion.txt.gz | pv -p --timer --rate --bytes --size 14388140441 | lz4 -1 -BI - /bigdata/billion/1.2billion.txt.lz4 2>/dev/null
```

## Analyze markers

### Weather

http://www.ncdc.noaa.gov/