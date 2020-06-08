drop table member_info;

create table member_info(
    id int primary key,
    messages int not null,
    nickname varchar(255) not null,
    hist jsonb
);