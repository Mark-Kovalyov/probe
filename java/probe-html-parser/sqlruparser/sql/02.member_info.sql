drop table member_info;

create table member_info(
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

create unique index nickname_idx on member_info(nickname);

CREATE INDEX hist_idx ON member_info USING gin(hist jsonb_path_ops);

-- Reports

select id, state, nickname, messages, registered, last_update, (select count(*) from json_object_keys(hist::json)) as cnt_hist from member_info order by cnt_hist desc;

select id, nickname, messages, registered, last_update, (select count(*) from json_object_keys(hist::json)) as cnt_hist from member_info order by messages desc;

select avg((select count(*) from json_object_keys(hist::json))) from member_info

select count(id), registered from member_info group by registered;