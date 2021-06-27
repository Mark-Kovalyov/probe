# Btrfs cheetsheet

## Create file system
```
$ mkfs.btrfs -f /dev/sdc3
```

```
$ mount -t /dev/sdc3 /btrfs1
$ chown -R user:user /btrfs1
```

## Usecase 2 extend volume to new device

```
Copy a lot of files:
$ cp ...... /btrfs1
```
Show usage:
```
$ btrfs filesystem usage /btrfs1
Overall:
    Device size:		 119.21GiB
    Device allocated:		  88.02GiB
    Device unallocated:		  31.19GiB
    Device missing:		     0.00B
    Used:			  86.26GiB
    Free (estimated):		  31.53GiB	(min: 15.94GiB)
    Data ratio:			      1.00
    Metadata ratio:		      2.00
    Global reserve:		 107.91MiB	(used: 0.00B)

Data,single: Size:86.01GiB, Used:85.66GiB
   /dev/sdc3	  86.01GiB

Metadata,DUP: Size:1.00GiB, Used:309.41MiB
   /dev/sdc3	   2.00GiB

System,DUP: Size:8.00MiB, Used:16.00KiB
   /dev/sdc3	  16.00MiB

Unallocated:
   /dev/sdc3	  31.19GiB
```
Add device:
```
$ btrfs device add -f /dev/sdc4 /btrfs1
```
Show usage:
```
btrfs filesystem usage /btrfs1
Overall:
    Device size:		 238.42GiB
    Device allocated:		  88.02GiB
    Device unallocated:		 150.40GiB
    Device missing:		     0.00B
    Used:			  86.26GiB
    Free (estimated):		 150.74GiB	(min: 75.54GiB)
    Data ratio:			      1.00
    Metadata ratio:		      2.00
    Global reserve:		 107.91MiB	(used: 0.00B)

Data,single: Size:86.01GiB, Used:85.66GiB
   /dev/sdc3	  86.01GiB

Metadata,DUP: Size:1.00GiB, Used:309.41MiB
   /dev/sdc3	   2.00GiB

System,DUP: Size:8.00MiB, Used:16.00KiB
   /dev/sdc3	  16.00MiB

Unallocated:
   /dev/sdc3	  31.19GiB
   /dev/sdc4	 119.21GiB
```

Rebalance:
```
$ btrfs filesystem balance /btrfs1
WARNING:

    Full balance without filters requested. This operation is very
    intense and takes potentially very long. It is recommended to
    use the balance filters to narrow down the scope of balance.
    Use 'btrfs balance start --full-balance' option to skip this
    warning. The operation will start in 10 seconds.
    Use Ctrl-C to stop it.
10 9 8 7 6 5 4 3 2 1
Starting balance without any filters.
Done, had to relocate 89 out of 89 chunks
```

Check usage again:
```
btrfs filesystem usage /btrfs1
Overall:
    Device size:		 238.42GiB
    Device allocated:		  88.06GiB
    Device unallocated:		 150.36GiB
    Device missing:		     0.00B
    Used:			  86.26GiB
    Free (estimated):		 150.70GiB	(min: 75.52GiB)
    Data ratio:			      1.00
    Metadata ratio:		      2.00
    Global reserve:		 107.33MiB	(used: 0.00B)

Data,single: Size:86.00GiB, Used:85.66GiB
   /dev/sdc3	  41.00GiB
   /dev/sdc4	  45.00GiB

Metadata,RAID1: Size:1.00GiB, Used:308.83MiB
   /dev/sdc3	   1.00GiB
   /dev/sdc4	   1.00GiB

System,RAID1: Size:32.00MiB, Used:16.00KiB
   /dev/sdc3	  32.00MiB
   /dev/sdc4	  32.00MiB

Unallocated:
   /dev/sdc3	  77.18GiB
   /dev/sdc4	  73.18GiB
```

Unmount and mount again:
```
umount /btrfs1
```

Scan and Show:
```
$ btrfs filesystem show
Label: 'bigdata'  uuid: a7adb15c-1100-49c4-82ff-13fb752e81ee
    Total devices 1 FS bytes used 3.72GiB
    devid    1 size 3.64TiB used 6.02GiB path /dev/sda

Label: none  uuid: 67d15d2b-ace3-497d-88b0-7fa961ed8ada
    Total devices 1 FS bytes used 1.40TiB
    devid    1 size 1.82TiB used 1.41TiB path /dev/sdb

Label: none  uuid: ede561bc-c752-4f7f-b680-9cd4375df0b0
    Total devices 2 FS bytes used 85.96GiB
    devid    1 size 119.21GiB used 42.05GiB path /dev/sdc3
    devid    2 size 119.21GiB used 46.03GiB path /dev/sdc4
```

Make label (mounted)
```
btrfs filesystem label <mountpoint> <newlabel>
```

Make label (unmounted)
```
btrfs filesystem label <device> <newlabel>
```

