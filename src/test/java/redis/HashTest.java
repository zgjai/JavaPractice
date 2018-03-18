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
 * Created by zhangguijiang on 2018/2/24.
 */
public class HashTest {
    private static Jedis client;

    @Before
    public void init() {
        client = new Jedis("localhost");
    }

    @Test
    public void testHash() {
        String key = "hash-key";
        client.del(key);

        Map<String, String> values = Maps.newHashMap();
        values.put("k1", "v1");
        values.put("k2", "v2");

        client.hmset(key, values);
        Assert.assertEquals(values, client.hgetAll(key));

        Assert.assertEquals(Lists.newArrayList("v1", "v2", null), client.hmget(key, "k1", "k2", "k3"));

        Assert.assertTrue(client.hexists(key, "k1"));

        Assert.assertEquals(Sets.newHashSet("k1", "k2"), client.hkeys(key));

        Assert.assertEquals(Sets.newHashSet("v1", "v2"), Sets.newHashSet(client.hvals(key)));

    }

}
