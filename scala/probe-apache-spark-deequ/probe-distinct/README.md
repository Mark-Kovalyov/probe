# Distinct

## Deploy

With Python
```
$ bin/spark-submit --master spark://host:7077 \
                   --executor-memory 2g \
                 script01.py
```

With Java
```
$ bin/spark-submit \
      --master spark:hostname:7077 \
      --deploy-mode cluster
      --
```

## URL

- spark://host:port
- mesos://host:port
- yarn
- local (local instance)
- local[N]

