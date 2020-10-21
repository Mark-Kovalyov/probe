create table saga_entry(
 id bigint not null
 association_key character varying(255) not null
 association_value character varying(255)
 saga_id character varying(255) not null
 saga_type character varying(255)
);