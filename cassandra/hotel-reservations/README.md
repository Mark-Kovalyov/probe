# Tutorial from "Apache Cassandra" - Jeff Carpenter, Eben Hewitt

## Hotel

```
create keyspace hotel with replication = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

use keyspace hotel;

create type address (
 street     text,
 state      text,
 postalcode text,
 country    text
);

create table hotels_by_poi(
 poi_name text,
 hotel_id text,
 name     text,
 phone    text,
 address  frozen<address>,
 primary key ((poi_name), hotel_id)
) with clustering order by (hotel_id asc);

create table hotels(
 id      text primary key,
 name    text,
 phone   text,
 address frozen<address>,
 pois    set<text>
); 

create table pois_by_hotel(
 poi_name    text,
 hotel_id    text,
 description text,
 primary key ((hotel_id), poi_name)
);

create table available_rooms_by_hotel_date(
 hotel_id     text,
 date         date,
 room_number  smallint,
 is_available boolean,
 primary key ((hotel_id), date, room_number)
);

create table amenities_by_room (
 hotel_id     text,
 room_number  smallint,
 amenity_name text,
 description  text,
 primary key ((hotel_id, room_number), amenity_name)
);
```

## Reservation

```
create keyspace reservation with replication = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

use reservation;

create table reservation_by_hotel_date(
 hotel_id text,
 start_date date,
 end_date date,
 room_number smallint,
 confirm_number text,
 guest_id uuid,
 primary key ((hotel_id, start_date), room_number)
);

create type address (
 street            text,
 city              text,
 state_or_provance text,
 postal_code       text,
 country           text
);

-- Find guest by ID
****create table guests(
 guest_id uuid primary key,
 first_name text,
 last_name text,
 title text,
 emails set<text>,
 phone_numbers list<text>,
 adresses map<text, frozen<address>>,
 confirm_number text
);****
```

test data for mv
```

insert into reservation_by_hotel_date(hotel_id, start_date, end_date, room_number, confirm_number, guest_id) values('Hilton',         '2021-06-01', '2021-06-08', 44, '555', 29da3a30-8fc5-429f-863c-08b965f834e9);
insert into reservation_by_hotel_date(hotel_id, start_date, end_date, room_number, confirm_number, guest_id) values('Hilton',         '2021-06-01', '2021-06-08', 45, '222', cee4e91f-41c9-44c6-a59b-425c207e8b27);
insert into reservation_by_hotel_date(hotel_id, start_date, end_date, room_number, confirm_number, guest_id) values('Hilton',         '2021-06-09', '2021-06-22', 44, '555', 96c4417a-a501-4e38-92a6-94c92136ddf0);
insert into reservation_by_hotel_date(hotel_id, start_date, end_date, room_number, confirm_number, guest_id) values('Diplomat Hotel', '2021-01-01', '2021-01-01', 12, '222', c56d27f9-ea41-4394-a418-e2fa12683a4c);

```

```
select * from reservation_by_hotel_date;

 hotel_id       | start_date | room_number | confirm_number | end_date   | guest_id
----------------+------------+-------------+----------------+------------+--------------------------------------
 Diplomat Hotel | 2021-01-01 |          12 |            222 | 2021-01-01 | c56d27f9-ea41-4394-a418-e2fa12683a4c
         Hilton | 2021-06-01 |          44 |            555 | 2021-06-08 | 29da3a30-8fc5-429f-863c-08b965f834e9
         Hilton | 2021-06-01 |          45 |            222 | 2021-06-08 | cee4e91f-41c9-44c6-a59b-425c207e8b27
         Hilton | 2021-06-09 |          44 |            555 | 2021-06-22 | 96c4417a-a501-4e38-92a6-94c92136ddf0
 
```

Select by partition keys
```
> select * from reservation_by_hotel_date where hotel_id = 'Hilton' and start_date = '2021-06-01';

hotel_id | start_date | room_number | confirm_number | end_date   | guest_id
----------+------------+-------------+----------------+------------+--------------------------------------
Hilton | 2021-06-01 |          44 |            555 | 2021-06-08 | 29da3a30-8fc5-429f-863c-08b965f834e9
Hilton | 2021-06-01 |          45 |            222 | 2021-06-08 | cee4e91f-41c9-44c6-a59b-425c207e8b27
```
Select by partition + clustered columns

```
> select * from reservation_by_hotel_date where hotel_id = 'Hilton' and start_date = '2021-06-01' and room_number > 44;

 hotel_id | start_date | room_number | confirm_number | end_date   | guest_id
----------+------------+-------------+----------------+------------+--------------------------------------
   Hilton | 2021-06-01 |          45 |            222 | 2021-06-08 | cee4e91f-41c9-44c6-a59b-425c207e8b27
```
Select with allow filtering
```
select * from reservation_by_hotel_date where guest_id = 29da3a30-8fc5-429f-863c-08b965f834e9 allow filtering;

 hotel_id | start_date | room_number | confirm_number | end_date   | guest_id
----------+------------+-------------+----------------+------------+--------------------------------------
   Hilton | 2021-06-01 |          44 |            555 | 2021-06-08 | 29da3a30-8fc5-429f-863c-08b965f834e9

```

## Mviews

```
create materialized view reservation_by_confirmation
as 
select * from reservation_by_hotel_date
where 
 confirm_number is not null and
 hotel_id is not null and
 start_date is not null and
 room_number is not null
 primary key (confirm_number, hotel_id, start_date, room_number);   
```

## Samples
```
insert into guests(guest_id,first_name,last_name,title,emails) values(uuid(),'Abraam','Katsman','Manager', {'katsman@gmail.com'});
insert into guests(guest_id,first_name,last_name,title,emails,phone_numbers) values(uuid(),'Abraam','Katsman','Manager', {'katsman@gmail.com'}, ['555-55-55']);
```