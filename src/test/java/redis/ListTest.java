package redis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.common.collect.Lists;

import redis.clients.jedis.Jedis;

/**
 * Created by zhangguijiang on 2018/2/24.
 */
@FixMethodOrder(MethodSorters.DEFAULT)
public class ListTest {
    private static Jedis client;

    @Before
    public void init() {
        client = new Jedis("localhost");
    }

    @Test
    public void testPush() {
        String key = "list-key";
        client.del(key);
        Assert.assertEquals(1, client.lpush(key, "first").intValue());

        Assert.assertEquals(3, client.rpush(key, "second", "third").intValue());

        Assert.assertEquals(Lists.newArrayList("first", "second", "third"), client.lrange(key, 0, -1));

        client.lpush(key, "a", "b", "c");
        Assert.assertEquals(Lists.newArrayList("c", "b", "a", "first", "second"), client.lrange(key, 0, -2));
    }

    @Test
    public void testPopIndexTrim() {
        String key = "list-key";

        Assert.assertEquals("c", client.lpop(key));

        Assert.assertEquals("third", client.rpop(key));

        Assert.assertEquals("second", client.lindex(key, -1));

        client.ltrim(key, 2, -2);
        Assert.assertEquals(Lists.newArrayList("first"), client.lrange(key, 1, -1));
    }
}
