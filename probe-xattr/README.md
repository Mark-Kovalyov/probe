# XATTR

Commands
* attr
* getfattr
* setfattr


```
#include <sys/types.h>
#include <sys/xattr.h>
#include <sys/types.h>
#include <sys/acl.h>

acl_get_link_np
listxattr
acl_get_entry
```


```
setfattr [-h] -n name [-v value] pathname...
setfattr [-h] -x name pathname...
setfattr [-h] --restore=file
```