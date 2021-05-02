create table elven.balances(
    id int PRIMARY KEY,
    elven_name TEXT NOT NULL,
    balance NUMERIC(12,2) NOT NULL CHECK(balance >= 0)
)