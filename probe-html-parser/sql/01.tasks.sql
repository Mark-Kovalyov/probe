-- TODO: Liquibase or FlyWay

drop table tasks;

create table tasks(
    id INT GENERATED ALWAYS AS IDENTITY,
    member_start int not null,
    member_end int not null,
    state varchar(30) -- ready, inprocess, finished
);

insert into tasks(member_start,member_end,state) values (0, 100, 'ready');
insert into tasks(member_start,member_end,state) values (100, 200, 'ready');

