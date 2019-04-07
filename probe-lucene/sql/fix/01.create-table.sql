create table fixlog(
  messageid varchar2(36) primary key,
  messagets timestamp(6) not null,
  message clob not null
);

CREATE OR REPLACE FUNCTION NEWID RETURN varchar2 
IS
    c varchar2(32);
BEGIN
    c:=lower(rawtohex(sys_guid()));
    return substr(c,1,8)||'-'||substr(c,8,4)||'-'||substr(c,12,4)||'-'||substr(c,16,4)||'-'||substr(c,20,12);
END NEWID;
/


insert into fixlog values(sys_guid(),systimestamp,empty_clob());
insert into fixlog values(sys_guid(),systimestamp,empty_clob());
insert into fixlog values(sys_guid(),systimestamp,empty_clob());
insert into fixlog values(sys_guid(),systimestamp,to_clob('8=FIX.4.4|9=126|35=A|49=theBroker.12345|56=CSERVER|34=1|52=20170117- 08:03:04|57=TRADE|50=any_string|98=0|108=30|141=Y|553=12345|554=passw0rd!|10=131|'));
insert into fixlog values(sys_guid(),systimestamp,to_clob('8=FIX.4.4|9=106|35=A|34=1|49=CSERVER|50=TRADE|52=20170117- 08:03:04.509|56=theBroker.12345|57=any_string|98=0|108=30|141=Y|10=066|'));
commit;


