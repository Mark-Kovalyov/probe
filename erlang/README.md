# Erlang

* OTP - Framework (Open Telecom Platform)

* Elixir - Erlang based language

## Erlang Modules

* io
* gen_udp
* gen_tcp

## REPL

```
erl
Erlang/OTP 22 [erts-10.6.4] [source] [64-bit] [smp:12:12] [ds:12:12:10] [async-threads:1]

Eshell V10.6.4  (abort with ^G)
```

## Compile .beam

```
c(Mod).
```

## UDP client-server

Host1
```
Erlang/OTP 22 [erts-10.6.4] [source] [64-bit] [smp:12:12] [ds:12:12:10] [async-threads:1]
Eshell V10.6.4  (abort with ^G)
2> {ok, Socket2} = gen_udp:open(65000,[binary, {active,false}]).
{ok,#Port<0.7>}
3> gen_udp:send(Socket2,{192,168,1,130},65000,"Hello world").
```
Host2
```
2> {ok, Socket2} = gen_udp:open(65000,[binary, {active,false}]).
{ok,#Port<0.7>}
3> gen_udp:recv(Socket2,0).
{ok,{{192,168,1,200},65000,<<"Hello world">>}}
```

## Modules

```
-module(gcdlcm).
-export([gcd/2,lcm/2]).

mod(X,Y) when X > 0 -> X rem Y;
mod(X,Y) when X < 0 -> Y + X rem Y;
mod(0,_) -> 0.

gcd(A,B) when B =/= 0 -> gcd(B,mod(A,B));
gcd(A,_) -> A.

lcm(A,B) -> (A * B) div (gcd(A,B)).
```

## Printing

```
io:fwrite("Hello world!~n", []).


```

## Logging

## Define records

```
-record(vector, {x :: double,
                 y :: double,
                 z :: double}).
```


