drop table dictionary;

create table dictionary(
    key INT GENERATED ALWAYS AS IDENTITY,
    value varchar(255)
);

create unique index value_idx on dictionary(value);
