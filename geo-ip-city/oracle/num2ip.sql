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
