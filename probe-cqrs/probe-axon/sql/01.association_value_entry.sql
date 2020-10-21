create table association_value_entry(
 id bigint not null plain 
 association_key character varying(255) not null extended 
 association_value character varying(255) extended 
 saga_id character varying(255) not null extended 
 saga_type character varying(255) extended 
);