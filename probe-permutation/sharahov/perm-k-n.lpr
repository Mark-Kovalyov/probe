program perm_k_n;

uses
  SysUtils;

procedure ShowCombinationDesc(p: pIntegerArray; len: integer);
var
  s: AnsiString;
  i: integer;
begin;
  for i:=0 to len-1 do s := s + AnsiChar(Ord('0') + byte(p^[i]));
  WriteLn(s);
end;

function GenerateCombinationDesc(p: pIntegerArray; n, k: integer): integer;
var
  i: integer;
  Result: Integer;
begin;
  Result:=0;
  i:=k;
  dec(i);
  while true do begin;
    while i>0 do begin;
      dec(n);
      p^[i]:=n;
      dec(i);
    end;
    repeat;
      dec(n);
      p^[0]:=n;
      inc(Result);                  //подсчет сочетаний
      ShowCombinationDesc(p, k);  //обработка очередного сочетания
    until n<=0;
    repeat;
      inc(i); if i>=k then exit;
      n:=p^[i];
    until i<n;
  end;
end;

var
  c: array of integer;
  p: pointer;
  n, k, cnt: integer;
begin;
  n:=6; k:=5;
  SetLength(c, k);
  p:=@c[0];
  cnt:=GenerateCombinationDesc(p, n, k);
  WriteLn(cnt);
end.