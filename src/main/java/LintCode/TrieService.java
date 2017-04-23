package LintCode;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by zhangguijiang on 2017/3/25.
 */

class TrieNode {
    public NavigableMap<Character, TrieNode> children;
    public List<Integer> top10;

    public TrieNode() {
        children = new TreeMap<Character, TrieNode>();
        top10 = new ArrayList<Integer>();
    }
}

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
    }
}