drop table member_info;

create table member_info(
    id int primary key,
    messages int not null,
    nickname varchar(255) not null,
    hist jsonb
);

drop table member_info2;

create table member_info2(
    id int primary key,
    state varchar(30) not null,
    nickname varchar(30) not null,
    messages int not null,
    hist jsonb not null,
    email varchar(255),
    job_details varchar(255),
    registered timestamp,
    last_update timestamp
);

create unique index nickname_idx on member_info2(nickname);

CREATE INDEX hist_idx ON member_info2 USING gin(hist jsonb_path_ops);

-- Migrate

insert into member_info2(id,messages,nickname,hist,state) select id,messages,nickname,hist,'UNKNOWN' from member_info;

-- Reports

select id, state, nickname, messages, registered, last_update, (select count(*) from json_object_keys(hist::json)) as cnt_hist from member_info2 order by cnt_hist desc;

select id, nickname, messages, registered, last_update, (select count(*) from json_object_keys(hist::json)) as cnt_hist from member_info2 order by messages desc;

select avg((select count(*) from json_object_keys(hist::json))) from member_info2

select count(id), registered from member_info2 group by registered;