package LintCode.SystemDesign.MapReduce;

import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Using map reduce to count word frequency.
 */
public class MRWordCount {
    public static class Map {
        public void map(String key, String value, OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            StringTokenizer tokenizer = new StringTokenizer(value);
            while (tokenizer.hasMoreTokens()) {
                output.collect(tokenizer.nextToken(), 1);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values, OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next();
            }
            output.collect(key, sum);
        }
    }

    abstract class OutputCollector<K, V> {
        public abstract void collect(K key, V value);
        // Adds a key/value pair to the output buffer
    }
}
