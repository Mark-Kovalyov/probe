CREATE TABLE City(id LONG PRIMARY KEY, name VARCHAR) WITH "template=replicated";

CREATE TABLE Person(id LONG, name VARCHAR, city_id LONG, PRIMARY KEY (id, city_id)) WITH "backups=1, affinityKey=city_id";

CREATE INDEX idx_city_name ON City (name);

CREATE INDEX idx_person_name ON Person (name);
