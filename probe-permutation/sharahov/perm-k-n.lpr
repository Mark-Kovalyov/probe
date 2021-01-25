type
  TCombination = array of integer;

//только для отладки
procedure ShowCombination(const c: TCombination);
var
  s: AnsiString;
  i: integer;
begin;
  for i:=0 to Length(c)-1 do s:=s+AnsiChar(Ord('A')+c[i]);
  //Form1.Memo1.Lines.Add(s);
  WriteLn(s);
  end;

procedure ProcessCombination(const c: TCombination);
begin;
  ShowCombination(c); //отладка - закомментировать при работе

  //тут делаем что-то с массивом
  end;

//перебор сочетаний из n элементов [0..n-1] по k в алфавитном порядке
procedure GenerateCombinations(n, k: integer);
var
  c: TCombination;
  i, istart, v, vlast: integer;
begin;
  if (k<=0) or (k>n) then exit;

  SetLength(c, k);
  for i:=0 to k-1 do c[i]:=i;
  ProcessCombination(c);

  //если существует более одного сочетания, то крутим цикл
  if k<n then begin;
    vlast:=c[0]-1+n;
    istart:=k-1;
    while true do begin;
      if c[k-1]<>vlast then istart:=k-1
      else begin;
        dec(istart); if istart<0 then break;
        end;
      i:=istart;
      v:=c[istart];
      repeat;
        inc(v); c[i]:=v; inc(i);
        until i>=k;
      ProcessCombination(c);
      end;
    end;
  end;

GenerateCombinations(5, 3);
