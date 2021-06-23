# SQL

## Transpose table and make comma separated

```
# select * from test;
   v
-------
 quick
 brown
 fox
 jump
 over
 lazy
 dog
(7 rows)

# SELECT string_agg(distinct v::text, ',' order by v desc) FROM test;
             string_agg             
------------------------------------
 quick,over,lazy,jump,fox,dog,brown
(1 row)
```


