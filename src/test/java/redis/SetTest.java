package redis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;

import redis.clients.jedis.Jedis;

/**
 * Created by zhangguijiang on 2018/2/24.
 */
public class SetTest {
    private static Jedis client;

    @Before
    public void init() {
        client = new Jedis("localhost");
    }

    @Test
    public void testSet() {
        String key = "set-key";
        client.del(key);

        Assert.assertEquals(3, client.sadd(key, "v1", "v2", "v3", "v1").intValue());

        Assert.assertEquals(Sets.newHashSet("v1", "v2", "v3"), client.smembers(key));

        Assert.assertTrue(client.sismember(key, "v1"));
        Assert.assertFalse(client.sismember(key, "v4"));

        client.spop(key, 1);
        Assert.assertEquals(2, client.scard(key).intValue());
    }

}
