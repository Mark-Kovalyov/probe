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
label
Done;
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
      //ShowCombinationDesc(p, k);  //обработка очередного сочетания
    until n<=0;
    repeat;
      inc(i); if i>=k then goto Done;
      n:=p^[i];
    until i<n;
  end;
  Done:
  GenerateCombinationDesc:=Result;
end;

var
  c: array of integer;
  p: pointer;
  n, k, cnt: integer;
begin;
  n:=600; k:=2;
  SetLength(c, k);
  p:=@c[0];
  cnt:=GenerateCombinationDesc(p, n, k);
  WriteLn(cnt);
end.