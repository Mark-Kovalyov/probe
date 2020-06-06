create table tasks(
    id INT GENERATED ALWAYS AS IDENTITY,
    member_start int not null,
    member_end int not null,
    state varchar(30) -- ready, inprocess, finished
);

