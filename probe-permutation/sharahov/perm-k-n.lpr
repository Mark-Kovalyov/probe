program perm_k_n;

uses
  SysUtils;

procedure ShowCombinationDesc(p: pIntegerArray; len: Int32);
var
  s: AnsiString;
  i: Int32;
begin;
  for i:=0 to len-1 do s := s + AnsiChar(Ord('0') + byte(p^[i]));
  WriteLn(s);
end;

function GenerateCombinationDesc(p: pIntegerArray; n, k: Int32): Int32;
var
  i: Int32;
  Result: Int32;
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
  c: array of Int32;
  p: pointer;
  n, k, cnt: Int32;
begin;
  n:=10000; k:=2;
  SetLength(c, k);
  p:=@c[0];
  cnt:=GenerateCombinationDesc(p, n, k);
  WriteLn(cnt);
end.