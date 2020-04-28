%% Low-level API for others to use
-export([
	ping/1,
	store/4,
	find_node/2,
	find_value/2
]).

ping(Peer) ->
    dht_net:ping(Peer).

store(Peer, Token, ID, Port) ->
    dht_net:store(Peer, Token, ID, Port).

find_node({IP, Port}, Node) ->
    dht_net:find_node({IP, Port}, Node).

find_value({IP, Port}, ID) ->
    dht_net:find_value({IP, Port}, ID).
