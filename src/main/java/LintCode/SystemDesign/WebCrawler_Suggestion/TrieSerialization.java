package LintCode.SystemDesign.WebCrawler_Suggestion;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Serialize and deserialize a trie (prefix tree, search on internet for more details).
 * 
 * You can specify your own serialization algorithm, the online judge only cares about whether you can successfully
 * deserialize the output from your own serialize function.
 */
public class TrieSerialization {
    public String serialize(TrieNode root) {
        // Write your code here
        if (root == null)
            return "";

        StringBuffer sb = new StringBuffer();
        sb.append("<");
        Iterator iter = root.children.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            TrieNode child = (TrieNode) entry.getValue();
            sb.append(key);
            sb.append(serialize(child));
        }
        sb.append(">");
        return sb.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly you serialized at method "serialize", that
     * means the data is not given by system, it's given by your own serialize method. So the format of data is designed
     * by yourself, and deserialize it here as you serialize it in "serialize" method.
     */
    public TrieNode deserialize(String data) {
        // Write your code here
        if (data == null || data.length() == 0)
            return null;

        TrieNode root = new TrieNode();
        TrieNode current = root;
        Stack<TrieNode> path = new Stack<TrieNode>();
        for (Character c : data.toCharArray()) {
            switch (c) {
            case '<':
                path.push(current);
                break;
            case '>':
                path.pop();
                break;
            default:
                current = new TrieNode();
                path.peek().children.put(c, current);
            }
        }
        return root;
    }

    class TrieNode {
        NavigableMap<Character, TrieNode> children;

        TrieNode() {
            children = new TreeMap<Character, TrieNode>();
        }
    }
}
