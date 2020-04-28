package mayton.network.dht;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 *     PING — used to verify that a node is still alive.
 *     STORE — Stores a (key, value) pair in one node.
 *     FIND_NODE — The recipient of the request will return the k nodes in his own buckets that are the closest ones to the requested key.
 *     FIND_VALUE — Same as FIND_NODE, but if the recipient of the request has the requested key in its store, it will return the corresponding value.
 */
public interface Dht {

    boolean ping(@NotNull String peer);

    void store(@NotNull String peer,@NotNull String token, @NotNull String id, int port);

    Optional<String> findNode(@NotNull String ip, int port, @NotNull String node);

    Optional<String> findValue(@NotNull String ip, int port, @NotNull String id);

}
