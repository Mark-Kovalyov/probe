-- TODO: Liquibase or FlyWay

drop table tasks;

create table tasks(
    id               INT GENERATED ALWAYS AS IDENTITY,
    member_start     int not null,
    member_end       int not null,
    state            varchar(30),
    last_update_time timestamp
);

create or replace function tasks_func() returns trigger as $$
begin
    NEW.last_update_time := localtimestamp;
    return new;
end;
$$
language plpgsql;

create trigger tasks_trig before insert or update on tasks for each row execute procedure tasks_func();
