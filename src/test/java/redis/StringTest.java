package redis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * Created by zhangguijiang on 2018/2/23.
 */
public class StringTest {

    private static Jedis client;

    @Before
    public void init() {
        client = new Jedis("localhost");
    }

    @Test
    public void testGetSet() {
        client.del("key");
        Assert.assertNull(client.get("key"));

        client.set("key", "value");
        Assert.assertEquals("value", client.get("key"));

        client.del("num");

        client.incrBy("num", 3);
        Assert.assertEquals("3", client.get("num"));

        client.decrBy("num", -2);
        Assert.assertEquals("5", client.get("num"));

        client.incrByFloat("num", -2.3);
        Assert.assertEquals("2.7", client.get("num"));
    }

    @Test
    public void testAppend() {
        String key = "key";
        String s1 = "hello ";
        String s2 = "world!";

        client.del(key);
        client.append(key, s1);
        Assert.assertEquals(s1, client.get(key));

        client.append(key, s2);
        Assert.assertEquals(s1 + s2, client.get(key));
    }

    @Test
    public void testSetRange() {
        String key = "key";
        String s = "Hello world!";
        String ss = ", how are you?";
        client.setrange(key, 0, "H");
        Assert.assertEquals(s, client.get(key));

        client.setrange(key, s.indexOf("!"), ss);
        Assert.assertEquals("Hello world, how are you?", client.get(key));
    }
}
