drop table quote;
create table quote(
    id varchar(255) primary key,
    "TYPE" varchar(42),
    HAS_NAME varchar(87),
    HAS_PERM_ID varchar(11),
    HAS_EXCHANGE_CODE varchar(3),
    HAS_EXCHANGE_TICKER varchar(20),
    HAS_RIC varchar(23),
    IS_QUOTE_OF varchar(33),
    IS_QUOTED_IN varchar(27),
    HAS_MIC varchar(4));
