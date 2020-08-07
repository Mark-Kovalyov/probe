# Text Search in PG

## DataTypes
- tsvector
- tsquery

## Rank functions
- ts_rank
- ts_rank_cd

## Indexes
- Gist support
- GIN

## Search with tsvect

```
SELECT to_tsvector('Brown cat sees other cat')

----------------------------
'brown':1 'cat':2,5 'see':3
```

## Search with tsquery

```
SELECT to_tsquery('brown&cat&!dog')

-------------------------
'brown' & 'cat' & !'dog'
```

## Select with matches

```
SELECT to_tsvector('Brown cat sees other cat') @@ to_tsquery('brown&cat&!dog')

?column? boolean
----------------
true

```

## Debug

```
SELECT * FROM TS_DEBUG('Hello Worlds! Превед медвед!');

```
