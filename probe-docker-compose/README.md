# Some interesting docker use cases

## Haskell

```
```

## MIT-Scheme

```
docker run -it kisom/mit-scheme mit-scheme
```

## Common Lisp

```

```

## SWI-Prolog

```
docker run -it swipl 
```

```
[user].
...
Ctrl+D
```


```
FROM swipl
COPY . /app
CMD ["swipl", "/app/start.pl"]
```

## Apache Jena SparkQL console

## Gremlin Console

