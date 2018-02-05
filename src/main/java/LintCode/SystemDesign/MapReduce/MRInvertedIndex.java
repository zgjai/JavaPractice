package LintCode.SystemDesign.MapReduce;

import java.util.Iterator;
import java.util.List;

/**
 * Use map reduce to build inverted index for given documents.
 */
public class MRInvertedIndex {

    public static class Map {
        public void map(String _, Document value, OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values, OutputCollector<String, List<Integer>> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<Integer> value);
        }
    }

    abstract class OutputCollector<K, V> {
        public abstract void collect(K key, V value);
        // Adds a key/value pair to the output buffer
    }

    class Document {
        int id;
        String content;
    }

}
