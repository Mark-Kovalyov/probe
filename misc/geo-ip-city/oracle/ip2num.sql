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
