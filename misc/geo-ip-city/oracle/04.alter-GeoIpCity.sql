-- COMPRESS FOR ALL OPERATIONS;
-- ROW STORE COMPRESS ADVANCED; Oracle 12c
-- COMPRESS FOR ARCHIVE LOW;
-- COMPRESS FOR QUERY LOW;

SET ECHO ON

SET TIMING ON

SET FEEDBACK ON

ALTER TABLE geoipcity ADD CONSTRAINT pk_geoip PRIMARY KEY (startIpNum,endIpNum);

create or replace function ip2num(ip varchar2)
return number deterministic
is
  w varchar2(3); 
  x varchar2(3);
  y varchar2(3);
  z varchar2(3);  
  p1 pls_integer;
  p2 pls_integer;
begin
  if length(ip)>15 then 
    return null;
  end if;  
  if length(ip)<7 then 
    return null;
  end if;
  p1:=1;

  p2:=INSTR(ip,'.',p1+1);
  w :=substr(ip,p1,p2-p1);
  p1:=p2;

  p2:=INSTR(ip,'.',p1+1);
  x :=substr(ip,p1+1,p2-p1-1);
  p1:=p2;  

  p2:=INSTR(ip,'.',p1+1);
  y :=substr(ip,p1+1,p2-p1-1);
  p1:=p2;  

  z :=substr(ip,p2+1);

  return 16777216*to_number(w)+65536*to_number(x)+256*to_number(y)+to_number(z);
end;
/


create or replace function num2ip(num binary_integer)
return varchar2 deterministic
is
  ip varchar2(15);
begin
  ip:='';
  ip:=ip||to_char(mod(trunc(num/16777216),256));
  ip:=ip||'.';
  ip:=ip||to_char(mod(trunc(num/65536),256));
  ip:=ip||'.';
  ip:=ip||to_char(mod(trunc(num/256),256));
  ip:=ip||'.';
  ip:=ip||to_char(mod(num,256));
  return ip;
end;
/

                
ALTER TABLE geoipcity ADD n_startip number generated always as (ip2num(startipnum));

ALTER TABLE geoipcity ADD n_endip   number generated always as (ip2num(endipnum));

CREATE INDEX N_STARTIP_INDEX ON GEOIPCITY(N_STARTIP);

CREATE INDEX N_ENDIP_INDEX ON GEOIPCITY(N_ENDIP);

-- EXEC DBMS_STATS.GATHER_TABLE_STATS(user,'GEOIPCITY',cascade => true);
