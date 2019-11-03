drop user geoip cascade;

create user geoip identified by geoip default tablespace users temporary tablespace temp;

grant connect to geoip;

grant resource to geoip;

grant create any view to geoip;

grant create any directory to geoip;

grant select any dictionary to geoip;

grant execute on dbms_lock to geoip;

disc

exit
