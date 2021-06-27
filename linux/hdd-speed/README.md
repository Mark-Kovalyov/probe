# HDD and SDD speed in practice

## Utils

* fio
* ioping

https://support.binarylane.com.au/support/solutions/articles/1000055889-how-to-benchmark-disk-i-o

## Examples:

General disk info:
```
$ hdparm -I /dev/???
```

Random read-write perf:
```
./fio --randrepeat=1 --ioengine=libaio --direct=1 --gtod_reduce=1 --name=test --filename=test --bs=4k --iodepth=64 --size=4G --readwrite=randrw --rwmixread=75
```

Random read perf:
```
./fio --randrepeat=1 --ioengine=libaio --direct=1 --gtod_reduce=1 --name=test --filename=test --bs=4k --iodepth=64 --size=4G --readwrite=randread
```



With IOPing:
```
./ioping -R /dev/???
```

## By devices

### Sata Hard Drives
|Model                                |Size   |Cached reads   |Buffered reads|SATA modes         |LS  |PS  |Cache|RPM |Seek(min/avg/max/mdev)         |NCQ|
|-------------------------------------|-------|---------------|--------------|-------------------|----|----|-----|----|-------------------------------|---|
|WDC WD40EZRZ-22GXCB0 (Blue)          |4 Tb   |9223.85 MB/sec |180.27 MB/sec |1.0,2.0,2.5,2.6,3.0| 512|4096| ?   |5400|4.88 ms/17.1 ms/41.1 ms/5.40 ms|yes|
|WDC WD20EURX-63T0FY0 (Green)         |2 Tb   |9391.60 MB/sec |139.04 MB/sec |1.0,2.0,2.5,2.6,3.0| 512|4096| ?   |5400|6.03 ms/20.4 ms/34.6 ms/5.15 ms|yes|
|WDC WD10EALX-089BA0                  |1 Tb   |10195.64 MB/sec|124.75 MB/sec |                   | 512| 512| ?   |7200|5.00 ms/18.4 ms/40.7 ms/6.47 ms|   |
|WDC WD40EZRZ-22GXCB0                 |500 Gb |10644.91 MB/sec|134.08 MB/sec |1.0,2.0,2.5,2.6,3.0| 512|4096| ?   |5400|6.04 ms/15.1 ms/31.0 ms/4.90 ms|   |
|Segate (ST500DM002-1BD142(KC48))     |500 Gb |10479.25 MB/sec|125.52 MB/sec |                   |    |    |     |    |                               |   |
|WDC WD800JD-60LSA5                   |80 Gb  |9882.41 MB/sec |57.62 MB/sec  |                   | 512| 512| 8M  |    |3.92 ms/14.1 ms/24.1 ms/4.08 ms|no |

Where:
* PS - Physical Sector Size
* LS - Logical Sector Size

### SSD drives

# With 
```
hdparm -Tt /dev/sda

Where:
 -t   Perform device read timings
 -T   Perform cache read timings
```

# Protocols

```
Segate (ST500DM002-1BD142(KC48))

# hdparm -Tt /dev/sdc1

/dev/sdc1:
 Timing cached reads:   20930 MB in  2.00 seconds = 10479.25 MB/sec
 Timing buffered disk reads: 378 MB in  3.01 seconds = 125.52 MB/sec
```

# Appendix

Hdparm tool:
```
hdparm - get/set hard disk parameters - version v9.54, by Mark Lord.

clue=6
Usage:  hdparm  [options] [device ...]

Options:
 -a   Get/set fs readahead
 -A   Get/set the drive look-ahead flag (0/1)
 -b   Get/set bus state (0 == off, 1 == on, 2 == tristate)
 -B   Set Advanced Power Management setting (1-255)
 -c   Get/set IDE 32-bit IO setting
 -C   Check drive power mode status
 -d   Get/set using_dma flag
 -D   Enable/disable drive defect management
 -E   Set cd/dvd drive speed
 -f   Flush buffer cache for device on exit
 -F   Flush drive write cache
 -g   Display drive geometry
 -h   Display terse usage information
 -H   Read temperature from drive (Hitachi only)
 -i   Display drive identification
 -I   Detailed/current information directly from drive
 -J   Get/set Western DIgital "Idle3" timeout for a WDC "Green" drive (DANGEROUS)
 -k   Get/set keep_settings_over_reset flag (0/1)
 -K   Set drive keep_features_over_reset flag (0/1)
 -L   Set drive doorlock (0/1) (removable harddisks only)
 -m   Get/set multiple sector count
 -M   Get/set acoustic management (0-254, 128: quiet, 254: fast)
 -n   Get/set ignore-write-errors flag (0/1)
 -N   Get/set max visible number of sectors (HPA) (VERY DANGEROUS)
 -p   Set PIO mode on IDE interface chipset (0,1,2,3,4,...)
 -P   Set drive prefetch count
 -q   Change next setting quietly
 -Q   Get/set DMA queue_depth (if supported)
 -r   Get/set device readonly flag (DANGEROUS to set)
 -R   Get/set device write-read-verify flag
 -s   Set power-up in standby flag (0/1) (DANGEROUS)
 -S   Set standby (spindown) timeout
 -t   Perform device read timings
 -T   Perform cache read timings
 -u   Get/set unmaskirq flag (0/1)
 -U   Obsolete
 -v   Use defaults; same as -acdgkmur for IDE drives
 -V   Display program version and exit immediately
 -w   Perform device reset (DANGEROUS)
 -W   Get/set drive write-caching flag (0/1)
 -x   Obsolete
 -X   Set IDE xfer mode (DANGEROUS)
 -y   Put drive in standby mode
 -Y   Put drive to sleep
 -z   Re-read partition table
 -Z   Disable Seagate auto-powersaving mode
 --dco-freeze      Freeze/lock current device configuration until next power cycle
 --dco-identify    Read/dump device configuration identify data
 --dco-restore     Reset device configuration back to factory defaults
 --dco-setmax      Use DCO to set maximum addressable sectors
 --direct          Use O_DIRECT to bypass page cache for timings
 --drq-hsm-error   Crash system with a "stuck DRQ" error (VERY DANGEROUS)
 --fallocate       Create a file without writing data to disk
 --fibmap          Show device extents (and fragmentation) for a file
 --fwdownload            Download firmware file to drive (EXTREMELY DANGEROUS)
 --fwdownload-mode3      Download firmware using min-size segments (EXTREMELY DANGEROUS)
 --fwdownload-mode3-max  Download firmware using max-size segments (EXTREMELY DANGEROUS)
 --fwdownload-mode7      Download firmware using a single segment (EXTREMELY DANGEROUS)
 --fwdownload-modee      Download firmware using mode E (min-size segments) (EXTREMELY DANGEROUS)
 --fwdownload-modee-max  Download firmware using mode E (max-size segments) (EXTREMELY DANGEROUS)
 --idle-immediate  Idle drive immediately
 --idle-unload     Idle immediately and unload heads
 --Istdin          Read identify data from stdin as ASCII hex
 --Istdout         Write identify data to stdout as ASCII hex
 --make-bad-sector Deliberately corrupt a sector directly on the media (VERY DANGEROUS)
 --offset          use with -t, to begin timings at given offset (in GiB) from start of drive
 --prefer-ata12    Use 12-byte (instead of 16-byte) SAT commands when possible
 --read-sector     Read and dump (in hex) a sector directly from the media
 --repair-sector   Alias for the --write-sector option (VERY DANGEROUS)
 --sanitize-antifreeze-lock  Block sanitize-freeze-lock command until next power cycle
 --sanitize-block-erase      Start block erase operation
 --sanitize-crypto-scramble  Change the internal encryption keys that used for used data
 --sanitize-freeze-lock      Lock drive's sanitize features until next power cycle
 --sanitize-overwrite  PATTERN  Overwrite the internal media with constant PATTERN
 --sanitize-status           Show sanitize status information
 --security-help             Display help for ATA security commands
 --trim-sector-ranges        Tell SSD firmware to discard unneeded data sectors: lba:count ..
 --trim-sector-ranges-stdin  Same as above, but reads lba:count pairs from stdin
 --verbose                   Display extra diagnostics from some commands
 --write-sector              Repair/overwrite a (possibly bad) sector directly on the media (VERY DANGEROUS)
```