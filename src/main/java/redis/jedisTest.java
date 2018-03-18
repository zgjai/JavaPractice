package redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

/**
 * Created by zhangguijiang on 2018/2/23.
 */
public class jedisTest {
    private static final Logger logger = LoggerFactory.getLogger(jedisTest.class);

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        logger.info("value={}", value);
        value = jedis.get("foo1");
        logger.info("value={}", value);
    }
}
