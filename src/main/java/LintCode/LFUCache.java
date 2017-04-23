package LintCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangguijiang on 2017/3/18.
 */
public class LFUCache {

    private int capacity;
    private Map<Integer, Integer> kvMap;
    private Map<Integer, Integer> countMap;

    // @param capacity, an integer
    public LFUCache(int capacity) {
        // Write your code here
        this.capacity = capacity;
        this.kvMap = new HashMap<Integer, Integer>();
        this.countMap = new HashMap<Integer, Integer>();
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        // Write your code here
        if (kvMap.size() >= capacity && !kvMap.keySet().contains(key)) {
            int min = Integer.MAX_VALUE;
            int oldK = 0;
            for (Map.Entry<Integer, Integer> item : countMap.entrySet()) {
                if (item.getValue() < min) {
                    min = item.getValue();
                    oldK = item.getKey();
                }
            }
            kvMap.remove(oldK);
            countMap.remove(oldK);
        }
        Integer count = countMap.get(key);
        if (count == null) {
            count = 0;
        }
        count++;
        countMap.put(key, count);
        kvMap.put(key, value);
    }

    public int get(int key) {
        // Write your code here
        Integer count = countMap.get(key);
        if (count == null) {
            return -1;
        }
        count++;
        countMap.put(key, count);
        return kvMap.get(key);
    }
}
