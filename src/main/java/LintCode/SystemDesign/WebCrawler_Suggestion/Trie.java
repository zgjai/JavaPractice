package LintCode.SystemDesign.WebCrawler_Suggestion;

import java.util.HashMap;

/**
 * Implement a trie with insert, search, and startsWith methods.
 */
public class Trie {

    TrieNode root;

    public Trie() {
        // do intialization if necessary
        root = new TrieNode();
    }

    /*
     * @param word: a word
     * @return: nothing
     */
    public void insert(String word) {
        // write your code here
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode(c));
            }
            cur = cur.children.get(c);
        }
        cur.hasWord = true;
    }

    /*
     * @param word: A string
     * @return: if the word is in the trie.
     */
    public boolean search(String word) {
        // write your code here
        if (searchWordNodePos(word) == null) {
            return false;
        } else {
            return searchWordNodePos(word).hasWord;
        }
    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        // write your code here
        return searchWordNodePos(prefix) != null;
    }

    private TrieNode searchWordNodePos(String s) {
        TrieNode cur = root;
        for (char c : s.toCharArray()) {
            if (cur.children.containsKey(c)) {
                cur = cur.children.get(c);
            } else {
                return null;
            }
        }
        return cur;
    }

    class TrieNode {
        char c;
        HashMap<Character, TrieNode> children = new HashMap<>();
        boolean hasWord = false;

        TrieNode() {
        }

        TrieNode(char c) {
            this.c = c;
        }
    }
}