# JSON/JSONB datatypes

```
create table tab1(
  doc jsonb
);

create index tab1_idx on tab1 USING GIN(doc);
```
