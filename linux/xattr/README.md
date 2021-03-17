# XATTR / ATTR

What is the differnence?

```
attr -s md5 -V 1234 filename 
setfattr -n user.ext-md5 -v 1235 -h filename
```

|FileSystem|mount option|attr|setfattr / getfattr|
|----------|------------|----|------|
|ZFS|
|XFS*|rw,relatime,attr2,inode64,logbufs=8,logbsize=32k,noquota|+|+
|Ext4*|rw,relatime,errors=remount-ro|+|+
|Btrfs*|rw,relatime,space_cache,subvolid=5,subvol=/|+|+
|ReiserFS|
|Reiser4|

## ATTR

```
$ attr 
A filename to operate on is required
Usage: attr [-LRSq] -s attrname [-V attrvalue] pathname  # set value
       attr [-LRSq] -g attrname pathname                 # get value
       attr [-LRSq] -r attrname pathname                 # remove attr
       attr [-LRq]  -l pathname                          # list attrs 
      -s reads a value from stdin and -g writes a value to stdout
```

## GETFATTR

```
$ setfattr --help
setfattr 2.4.48 -- set extended attributes
Usage: setfattr {-n name} [-v value] [-h] file...
       setfattr {-x name} [-h] file...
  -n, --name=name         set the value of the named extended attribute
  -x, --remove=name       remove the named extended attribute
  -v, --value=value       use value as the attribute value
  -h, --no-dereference    do not dereference symbolic links
      --restore=file      restore extended attributes
      --raw               attribute value is not encoded
      --version           print version and exit
      --help              this help text
```

Samples
```
$ getfattr -d -m - setfattr.tst
$ 
$ setfattr -n user.mytag -v somevalue  setfattr.tst
$ 
$ getfattr -d -m - setfattr.tst

# file: setfattr.tst
user.mytag="somevalue"

$ setfattr -n user.md5 -v 898273895726387593245 setfattr.tst
$ 
$ getfattr -d -m - setfattr.tst
# file: setfattr.tst
user.md5="898273895726387593245"
user.mytag="somevalue"
```

## SETFACL

```
$ setfacl --help
setfacl 2.2.53 -- set file access control lists
Usage: setfacl [-bkndRLP] { -m|-M|-x|-X ... } file ...
  -m, --modify=acl        modify the current ACL(s) of file(s)
  -M, --modify-file=file  read ACL entries to modify from file
  -x, --remove=acl        remove entries from the ACL(s) of file(s)
  -X, --remove-file=file  read ACL entries to remove from file
  -b, --remove-all        remove all extended ACL entries
  -k, --remove-default    remove the default ACL
      --set=acl           set the ACL of file(s), replacing the current ACL
      --set-file=file     read ACL entries to set from file
      --mask              do recalculate the effective rights mask
  -n, --no-mask           don't recalculate the effective rights mask
  -d, --default           operations apply to the default ACL
  -R, --recursive         recurse into subdirectories
  -L, --logical           logical walk, follow symbolic links
  -P, --physical          physical walk, do not follow symbolic links
      --restore=file      restore ACLs (inverse of `getfacl -R')
      --test              test mode (ACLs are not modified)
  -v, --version           print version and exit
  -h, --help              this help text
```

## File permissions

```
getfacl -R Concrete5 > permissions.acl
setfacl --restore=permissions.acl
```
Configure ACL
```
UUID=95b26917-535e-46ac-8d72-443d46184bb5  /storage  ext3  grpquota,acl,suid,dev,usrquota,relatime,exec  0  2
```