package LintCode.SystemDesign.WebCrawler_Suggestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Build tries from a list of pairs. Save top 10 for each node.
 */
public class TrieService {

    private TrieNode root = null;

    public TrieService() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        // Return root of trie root, and
        // lintcode will print the tree struct.
        return root;
    }

    // @param word a string
    // @param frequency an integer
    public void insert(String word, int frequency) {
        // Write your cod here
        TrieNode cur = getRoot();
        for (char c : word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
            addFrequency(cur.top10, frequency);
        }
    }

    private void addFrequency(List<Integer> top10, int frequency) {
        top10.add(frequency);
        int index = top10.size() - 1;
        while (index > 0) {
            if (top10.get(index) > top10.get(index - 1)) {
                int tmp1 = top10.get(index);
                int tmp2 = top10.get(index - 1);
                top10.set(index - 1, tmp1);
                top10.set(index, tmp2);
                index--;
            } else {
                break;
            }
        }
        if (top10.size() > 10) {
            top10.remove(top10.size() - 1);
        }
    }

    class TrieNode {
        NavigableMap<Character, TrieNode> children;
        List<Integer> top10;

        TrieNode() {
            children = new TreeMap<Character, TrieNode>();
            top10 = new ArrayList<Integer>();
        }
    }

    public static void main(String[] args) {
        NavigableMap<String, Integer> navigableMap = new TreeMap<>();
        Map<String, Integer> map = new HashMap<>();
        map.put("c", 3);
        map.put("a", 1);
        map.put("ba", 5);
        map.put("b", 8);
        navigableMap.putAll(map);
        System.out.println(navigableMap);
        System.out.println(map);
    }
}