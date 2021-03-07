# Links

C Program for Red Black Tree Insertion
- https://www.geeksforgeeks.org/c-program-red-black-tree-insertion/

Eternally Confuzzled - R&B Tree Tutor
- https://web.archive.org/web/20070927222509/http://eternallyconfuzzled.com/tuts/datastructures/jsw_tut_rbtree.aspx

Б, Б+ и Б++ деревья
- http://algolist.manual.ru/ds/s_btr.php

# Articles

Ulrich Drepper - What Every Programmer Should Know About Memory 
- https://akkadia.org/drepper/cpumemory.pdf


## posix_memalign

posix_memalign,  aligned_alloc,  memalign, valloc, pvalloc - allocate  aligned memory
```
#include <stdlib.h>

int posix_memalign(void **memptr, size_t alignment, size_t size);
void *aligned_alloc(size_t alignment, size_t size);
void *valloc(size_t size);

#include <malloc.h>

void *memalign(size_t alignment, size_t size);
void *pvalloc(size_t size);
```


## CPU caches

```
$ getconf -a | grep CACHE
LEVEL1_ICACHE_SIZE                 65536 = 64k
LEVEL1_ICACHE_ASSOC                4
LEVEL1_ICACHE_LINESIZE             64

LEVEL1_DCACHE_SIZE                 32768 = 32k
LEVEL1_DCACHE_ASSOC                8
LEVEL1_DCACHE_LINESIZE             64

LEVEL2_CACHE_SIZE                  524288 = 512k
LEVEL2_CACHE_ASSOC                 8
LEVEL2_CACHE_LINESIZE              64

LEVEL3_CACHE_SIZE                  16777216 = 16M
LEVEL3_CACHE_ASSOC                 16
LEVEL3_CACHE_LINESIZE              64
LEVEL4_CACHE_SIZE                  0
LEVEL4_CACHE_ASSOC                 0
LEVEL4_CACHE_LINESIZE              0
```

