# XATTR

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
