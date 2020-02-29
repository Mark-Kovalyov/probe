type
  THexagon= packed record
    Height: byte;
    Level: byte;
    Dir: byte;
    end;

  TCoordinates= packed record
    CX: word;
    CY: word;
    end;

const
  XCount= 10000;
  YCount= XCount;
  XYCount= XCount * YCount;

var
  HexMap: packed array[0..YCount-1, 0..XCount-1] of THexagon;
  Order: packed array[0..YCount*XCount-1] of TCoordinates;

procedure ReadHexMap;
var
  x, y: integer;
begin;
  RandSeed:=0;
  for y:=0 to YCount-1 do for x:=0 to XCount-1 do HexMap[y,x].Height:=Random(256);
  end;

procedure SortHexMap;
var
  Counts: array[byte] of integer;
  h, x, y, sum, cnt: integer;
begin;
  for h:=Low(Counts) to High(Counts) do Counts[h]:=0;
  for y:=0 to YCount-1 do for x:=0 to XCount-1 do inc(Counts[HexMap[y,x].Height]);
  sum:=0;
  for h:=Low(Counts) to High(Counts) do begin;
    cnt:=sum; sum:=sum+Counts[h]; Counts[h]:=cnt;
    end;
  for y:=0 to YCount-1 do for x:=0 to XCount-1 do begin;
    h:=HexMap[y,x].Height; cnt:=Counts[h]; Counts[h]:=cnt+1;
    with Order[cnt] do begin; CX:=x; CY:=y; end;
    end;
  end;

procedure SetLevel(y, x: integer);
const
  dx: array[0..5] of integer= (-1, +1,  0, +1, +1,  0);
  dy: array[0..5] of integer= ( 0,  0, -1, +1, -1, +1);
var
  i, x2, y2: integer;
  cut: byte;
begin;
  cut:=HexMap[y,x].Level;
  HexMap[y,x].Dir:=Length(dx);
  i:=0;
  while true do begin;
    while i<Length(dx) do begin;
      x2:=x+dx[i]; if i>=2 then x2:=x2 + y and 1 - 1;
      y2:=y+dy[i];
      if (cardinal(y2)<YCount) and (cardinal(x2)<XCount)
      then with HexMap[y2,x2] do if Level>cut then begin;
        if Height>cut then Level:=Height
        else begin;
          Level:=cut; Dir:=i xor 1; i:=-1;
          x:=x2; y:=y2;
          end;
        end;
      inc(i);
      end;
    i:=HexMap[y,x].Dir;
    if i>=Length(dx) then break;
    x:=x+dx[i]; if i>=2 then x:=x + y and 1 - 1;
    y:=y+dy[i];
    i:=i xor 1 + 1;
    end;
  end;

function FillWater: int64;
var
  i, x, y, MaxHeight: integer;
begin;
  ReadHexMap;
  SortHexMap;
  with Order[XYCount-1] do MaxHeight:=HexMap[CY,CX].Height;
  for y:=0 to YCount-1 do for x:=0 to XCount-1 do with HexMap[y,x] do
    if (y=0) or (y=YCount-1) or (x=0) or (x=XCount-1)
    then Level:=Height
    else Level:=MaxHeight;
  for i:=0 to XYCount-1 do with Order[i] do with HexMap[CY,CX] do
    if Level=Height then SetLevel(CY,CX);
  Result:=0;
  for y:=0 to YCount-1 do for x:=0 to XCount-1 do with HexMap[y,x] do Result:=Result+(Level-Height);
  end;

BEGIN
   writeln('function returns: ',FillWater());
   readln;
END.
