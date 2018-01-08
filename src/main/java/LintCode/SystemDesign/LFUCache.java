package LintCode.SystemDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangguijiang on 2017/3/18.
 */
public class LFUCache {

    /**
     * LFU (Least Frequently Used) is a famous cache eviction algorithm.
     * 
     * For a cache with capacity k, if the cache is full and need to evict a key in it, the key with the lease
     * frequently used will be kicked out.
     * 
     * Implement set and get method for LFU cache.
     */

    private Map<Integer, CacheNode> cache;
    private List<LinkedHashSet<CacheNode>> frequencyList;
    private int maxFrequency;
    private int lowestFrequency;
    private final int cacheSize;

    public LFUCache(int capacity) {
        this.cacheSize = capacity;
        this.lowestFrequency = 0;
        this.maxFrequency = capacity * 2;
        this.frequencyList = new ArrayList<>(maxFrequency + 1);
        cache = new HashMap<>();
        initFrequencyList();
    }

    private void initFrequencyList() {
        for (int i = 0; i <= maxFrequency; i++) {
            frequencyList.add(i, new LinkedHashSet<>());
        }
    }

    public void set(int key, int value) {
        CacheNode node = cache.get(key);
        if (node == null) {
            node = new CacheNode(key, value, 0);
            if (cache.size() >= cacheSize) {
                doEviction();
            }
            cache.put(key, node);
            lowestFrequency = 0;
        } else {
            node.v = value;
        }
        addFrequency(node);
    }

    public int get(int key) {
        CacheNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        addFrequency(node);
        return node.v;
    }

    private void addFrequency(CacheNode curNode) {
        int curFrequency = curNode.frequency;
        if (curFrequency < maxFrequency) {
            int nextFrequency = curFrequency + 1;
            LinkedHashSet<CacheNode> curNodes = frequencyList.get(curFrequency);
            LinkedHashSet<CacheNode> nextNodes = frequencyList.get(nextFrequency);
            moveToNextFrequency(curNodes, nextNodes, nextFrequency, curNode);
            cache.put(curNode.k, curNode);
            if (curFrequency == lowestFrequency && curNodes.isEmpty()) {
                lowestFrequency = nextFrequency;
            }
        } else {
            LinkedHashSet<CacheNode> nodes = frequencyList.get(curFrequency);
            nodes.remove(curNode);
            nodes.add(curNode);
        }
    }

    private void moveToNextFrequency(LinkedHashSet<CacheNode> curNodes, LinkedHashSet<CacheNode> nextNodes,
            int nextFrequency, CacheNode curNode) {
        curNodes.remove(curNode);
        nextNodes.add(curNode);
        curNode.frequency = nextFrequency;
    }

    private void doEviction() {
        LinkedHashSet<CacheNode> lowestNodes = frequencyList.get(lowestFrequency);
        if (lowestNodes.isEmpty()) {
            return;
        }
        Iterator<CacheNode> it = lowestNodes.iterator();
        if (it.hasNext()) {
            cache.remove(it.next().k);
            it.remove();
        }
        findNextLowestNodes();
    }

    private void findNextLowestNodes() {
        while (lowestFrequency <= maxFrequency && frequencyList.get(lowestFrequency).isEmpty()) {
            lowestFrequency++;
        }
        if (lowestFrequency > maxFrequency) {
            lowestFrequency = 0;
        }
    }

    private class CacheNode {
        final int k;
        int v;
        int frequency;

        CacheNode(int k, int v, int frequency) {
            this.k = k;
            this.v = v;
            this.frequency = frequency;
        }
    }
}
