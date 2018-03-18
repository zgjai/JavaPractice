package redis;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import redis.clients.jedis.Jedis;

/**
 * Created by zhangguijiang on 2018/2/26.
 */
public class OrderedSetTest {
    private static Jedis client;

    @Before
    public void init() {
        client = new Jedis("localhost");
    }

    @Test
    public void testOrderedSet() {
        String key = "zset-key";
        client.del(key);

        Map<String, Double> values = Maps.newHashMap();
        values.put("k1", 1.0);
        values.put("k2", 2.0);

        client.zadd(key, values);

        Assert.assertEquals(Sets.newHashSet("k1", "k2"), client.zrange(key, 0, -1));

        client.zadd(key, 3.0, "k3");
        Assert.assertEquals(Sets.newHashSet("k1", "k2"), client.zrangeByScore(key, 1.0, 2.5));

        client.zincrby(key, 5, "k2");
        Assert.assertEquals(Lists.newArrayList("k1", "k3", "k2"), Lists.newArrayList(client.zrange(key, 0, -1)));

        Assert.assertEquals(2, client.zrank(key, "k2").intValue());
        Assert.assertEquals(0, client.zrank(key, "k1").intValue());
    }
}
