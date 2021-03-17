# XFS

## XFS copy

xfs_copy - copy the contents of an XFS filesystem



## Repair

```
xfs_repair /dev/sd?
```

## Add label

```
xfs_io -c label -s "MYNEWLABEL" /MNTPOINT
```

## Incremental backup with level

```
xfsdump -l 0 -f /root/dump0.xfsdump /mnt

.... changes

xfsdump -l 1 -f /root/dump1.xfsdump /mnt
```

## Restore (Simple, Cumulative, Interactive)

### Simple

```
xfsrestore -f /root/dump0.xfsdump /mnt
```

### Full (dump0 - is first)

```
xfsrestore -r -f /root/dump0.xfsdump /mnt
```

### Interactive (i)

```
xfsrestore -i ....
```
