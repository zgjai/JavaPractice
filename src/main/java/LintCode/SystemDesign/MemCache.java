package LintCode.SystemDesign;

/**
 * Created by zhangguijiang on 2017/3/18.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 实现下列几个方法：
 * 
 * 1.get(curtTime, key). 得到key的值，如果不存在返回2147483647
 * 
 * 2.set(curtTime, key, value, ttl). 设置一个pair(key,value)，有效期从curtTime到curtTime + ttl -1 , 如果ttl为0，则一直存在
 * 
 * 3.delete(curtTime, key). 删除这个key
 * 
 * 4.incr(curtTime, key, delta). 给key的value加上delta，并且返回 如果不存在返回 2147483647。
 * 
 * 5.decr(curtTime, key, delta). 给key的value减去delta，并且返回 如果不存在返回 2147483647。
 */
public class MemCache {

    private Map<Integer, Integer> kvMap = new HashMap<Integer, Integer>();
    private Map<Integer, Integer> timeMap = new HashMap<Integer, Integer>();
    private final Integer errV = 2147483647;

    public MemCache() {
        // Initialize your data structure here.
    }

    public int get(int curtTime, int key) {
        // Write your code here
        Integer v = kvMap.get(key);
        if (v == null) {
            return errV;
        }
        Integer time = timeMap.get(key);
        if (time != 0 && curtTime > time) {
            return errV;
        }
        return v;
    }

    public void set(int curtTime, int key, int value, int ttl) {
        // Write your code here
        kvMap.put(key, value);
        if (ttl == 0) {
            timeMap.put(key, 0);
        } else {
            timeMap.put(key, curtTime + ttl - 1);
        }
    }

    public void delete(int curtTime, int key) {
        // Write your code here
        kvMap.remove(key);
        timeMap.remove(key);
    }

    public int incr(int curtTime, int key, int delta) {
        // Write your code here
        Integer v = kvMap.get(key);
        if (v == null) {
            return errV;
        }
        Integer time = timeMap.get(key);
        if (time != 0 && curtTime > time) {
            return errV;
        }
        Integer newV = v + delta;
        kvMap.put(key, v + delta);
        return newV;
    }

    public int decr(int curtTime, int key, int delta) {
        // Write your code here
        return incr(curtTime, key, -delta);
    }
}
