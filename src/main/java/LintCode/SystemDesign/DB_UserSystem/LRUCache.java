package LintCode.SystemDesign.DB_UserSystem;

/**
 * Created by zhangguijiang on 2017/3/18.
 */

import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 为最近最少使用（LRU）缓存策略设计一个数据结构，它应该支持以下操作：获取数据（get）和写入数据（set）。
 * 
 * 获取数据get(key)：如果缓存中存在key，则获取其数据值（通常是正数），否则返回-1。
 * 
 * 写入数据set(key, value)：如果key还没有在缓存中，则写入其数据值。当缓存达到上限，它应该在写入新数据之前删除最近最少使用的数据用来腾出空闲位置。
 */
public class LRUCache {

    private int capacity;
    private Vector<Integer> keyList;
    private Map<Integer, Integer> kvMap;

    // @param capacity, an integer
    public LRUCache(int capacity) {
        // write your code here
        this.capacity = capacity;
        this.keyList = new Vector<Integer>();
        this.kvMap = new ConcurrentHashMap<Integer, Integer>();
    }

    // @return an integer
    public int get(int key) {
        // write your code here
        if (!keyList.contains(key)) {
            return -1;
        }
        keyList.remove(keyList.indexOf(key));
        keyList.add(key);
        return kvMap.get(key);
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        // write your code here
        if (keyList.size() >= capacity && !keyList.contains(key)) {
            int oldK = keyList.get(0);
            keyList.remove(0);
            kvMap.remove(oldK);
        }
        kvMap.put(key, value);
        if (!keyList.contains(key)) {
            keyList.add(key);
        } else {
            get(key);
        }
    }

}
