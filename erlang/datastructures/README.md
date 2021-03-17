# Erlang datastructures (draft)

## Physical representation
|Name|Internal data struct|Immutability|Implementation|Key order|Life time|
|----|--------------------|------------|--------------|---------|---------|
|Proplists|erlang-lists|immutable|erlang|as is
|ETS tables|?|?|?|?|Current process only
|Dict(dict)|?|immutable|?|Unordered(hash?)
|Ordered Dict(orddict)|?|immutable|?|Ordered|?|
|Maps|?|?|?|?|?
|Mnesia|

## Complexity
|Name|Insert|
|----|--------------------|
|Proplists|
|ETS tables|
|Dict(dict)|
|Ordered Dict(ordict)|
|Maps|
|Mnesia|


## Props (property lists)
```
P=[
{ id7369, "SMITH" },
{ id7499, "ALLEN" },
{ id7521, "WARD" },
{ id7566, "JONES" },
{ id7654, "MARTIN" },
{ id7698, "BLAKE" },
{ id7782, "CLARK" },
{ id7788, "SCOTT" },
{ id7839, "KING" },
{ id7844, "TURNER" },
{ id7876, "ADAMS" },
{ id7900, "JAMES" },
{ id7902, "FORD" },
{ id7934, "MILLER" }].

proplists:get_value(id7839,P).

proplists:get_all_values(id7839,P).

proplists:delete(id7369,P).

proplists:lookup()

proplists:lookup_all()
```

## ETS tables
```
T1 = etc:new(my_tab1, []).
T1 = etc:insert(T1, { 7369, "SMITH", "CLERK" ,7902, "17-DEC-1980", 800, "" ,20 }).
```

## Dict

```
D0 = dict:new().
D1 = dict:store(id7369, "SMITH", D0).
D2 = dict:store(id7499, "ALLEN", D1).
D3 = dict:store(id7521, "WARD", D2).
D4 = dict:store(id7566, "JONES", D3).
D5 = dict:store(id7654, "MARTIN", D4).
D6 = dict:store(id7698, "BLAKE", D5).
D7 = dict:store(id7782, "CLARK", D6).
D8 = dict:store(id7788, "SCOTT", D7).
D9 = dict:store(id7839, "KING", D8).
D10 = dict:store(id7844, "TURNER", D9).
D11 = dict:store(id7876, "ADAMS", D10).
D12 = dict:store(id7900, "JAMES", D11).
D13 = dict:store(id7902, "FORD", D12).
D14 = dict:store(id7934, "MILLER", D13).
dict:to_list(D14).
```

fetch (throws exception otherwise):
```
dict:fetch(id7844, D14).
```

from List
```
.. = dict:from_list(..)
```

yet another find (returns error):
```
dict:find(id7844, D14).
```
delete:
```
D15 = dict:erase(id7844, D14).
```
append multivalue to value:
```
D20 = dict:append(id7876, "BATMAN", D13).
dict:to_list(D20).
```
append list:
```
D21 = dict:append(id7876, ["ROBIN"], D20).
dict:to_list(D21).
```
apply map:
```
D22 = dict:map(fun(K,V) -> string:to_lower(V) end, D14).
dict:to_list(D22).
```
apply filter
```
D23 = dict:filter(fun(K,V) -> V =:= "KING" end, D14).
dict:to_list(D23).
```
fold:
```
D24 = dict:fold(fun(K,V,Acc) -> [V | Acc] end, [], D14).
dict:to_list(D24).
```
_Pay attention! Due to unsorted order of dict, we're unable_
_to do fold-left or fold-right. So we must use fold._

## Orddict

* The same API as Dict. 
* Ordered keys.
* Requires manual call to re-balance tree.

## GB-Trees

```
T0 = gb_trees:empty().
T1 = gb_trees:insert(7369, { "SMITH", "CLERK" }, T0).
T2 = gb_trees:insert(7499, { "ALLEN", "SALESMAN" }, T1).
T3 = gb_trees:insert(7521, { "WARD", "SALESMAN" }, T2).
T4 = gb_trees:insert(7566, { "JONES", "MANAGER" }, T3).
T5 = gb_trees:insert(7654, { "MARTIN", "SALESMAN" }, T4).
T6 = gb_trees:insert(7698, { "BLAKE", "MANAGER" }, T5).
T7 = gb_trees:insert(7782, { "CLARK", "MANAGER" }, T6).
T8 = gb_trees:insert(7788, { "SCOTT", "ANALYST" }, T7).
T9 = gb_trees:insert(7839, { "KING", "PRESIDENT" }, T8).
T10 = gb_trees:insert(7844, { "TURNER", "SALESMAN" }, T9).
T11 = gb_trees:insert(7876, { "ADAMS", "CLERK" }, T10).
T12 = gb_trees:insert(7900, { "JAMES", "CLERK" }, T11).
T13 = gb_trees:insert(7902, { "FORD", "ANALYST" }, T12).
T14 = gb_trees:insert(7934, { "MILLER", "CLERK" }, T13).
gb_trees:to_list(T14).
```
Update:
```
.. = gb_trees:update(..)
```
Upsert:
```
.. = gb_trees:enter(..)
```
Lookup and Get (different behaviour during error handling)
```
.. = gb_trees:get(..)
.. = gb_trees:lookup(..)
```
Delete:
```
.. = gb_trees:delete(..)
.. = gb_trees:delete_any(..)
```

## Maps

```
M = maps:new().

```

## Mnesia


## Test data

```
```