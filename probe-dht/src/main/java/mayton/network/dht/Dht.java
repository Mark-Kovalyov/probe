package mayton.network.dht;

import org.jetbrains.annotations.NotNull;

/**
 *     PING — used to verify that a node is still alive.
 *     STORE — Stores a (key, value) pair in one node.
 *     FIND_NODE — The recipient of the request will return the k nodes in his own buckets that are the closest ones to the requested key.
 *     FIND_VALUE — Same as FIND_NODE, but if the recipient of the request has the requested key in its store, it will return the corresponding value.
 */
public interface Dht {

    boolean ping(@NotNull String nodeIp);

    void store();

    // Transmisssion :: Received DHT UDP packet : from /*:26686 with data:
    //64 31 3A 61 64 32 3A 69 64 32 30 3A A5 4A 3E 05 C9  : d1:ad2:id20: J>
    //32 B3 B4 4A 08 F8 75 DD D0 B6 84 0F 5D EA 70 36 3A  : 2  J  u     ] p6:
    //74 61 72 67 65 74 32 30 3A A5 76 6F 79 5C 75 93 F0  : target20: voy\\u
    //09 DC 15 09 46 A6 A6 33 2F 4D 98 87 65 31 3A 71 39  :     F  3/M  e1:q9
    //3A 66 69 6E 64 5F 6E 6F 64 65 31 3A 74 32 3A 13 0D  : :find_node1:t2:
    //31 3A 76 34 3A 4C 54 01 00 31 3A 79 31 3A 71 65 31  : 1:v4:LT  1:y1:qe1
    //3A 76 34 3A 55 54 B2 3C 31 3A 79 31 3A 71           : :v4:UT <1:y1:q

    void findNode(@NotNull String nodeId);

    void findValue();

    // Transmisssion :: Received DHT UDP packet : from /*:63932 with data:
    //64 31 3A 61 64 32 3A 69 64 32 30 3A 1E D9 88 5F 62  : d1:ad2:id20:   _b
    //BD 0E FB D8 40 2F 2E B2 09 D0 84 7A 21 3A 10 39 3A  :     @/.    z!: 9:
    //69 6E 66 6F 5F 68 61 73 68 32 30 3A 1E D9 88 70 DE  : info_hash20:   p
    //7B 82 77 B3 5E 22 E8 69 88 72 1D F6 5E 7B ED 65 31  : { w ^" i r  ^{ e1
    //3A 71 39 3A 67 65 74 5F 70 65 65 72 73 31 3A 74 32  : :q9:get_peers1:t2
    //3A 4C 72 31 3A 76 34 3A 4C 54 01 25 31 3A 79 31 3A  : :Lr1:v4:LT %1:y1:
    //71 65 34 3A 55 54 B2 3C 31 3A 79 31 3A 71           : qe4:UT <1:y1:q

    @NotNull Iterable<String> getPeers();
}
