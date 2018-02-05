package LintCode.SystemDesign.MapReduce;

import java.util.Iterator;
import java.util.List;

/**
 * Use Map Reduce to find anagrams in a given list of words.
 */
public class MRAnagram {
    public static class Map {
        public void map(String key, String value, OutputCollector<String, String> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, String value);
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<String> values, OutputCollector<String, List<String>> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<String> value);
        }
    }

    abstract class OutputCollector<K, V> {
        public abstract void collect(K key, V value);
        // Adds a key/value pair to the output buffer
    }

}
