package mayton.probe.redis;

import redis.clients.jedis.Jedis;

public class RedisStandalone {

    public static void main(String[] args ) {
        Jedis jedis = new Jedis("localhost");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
    }

}
