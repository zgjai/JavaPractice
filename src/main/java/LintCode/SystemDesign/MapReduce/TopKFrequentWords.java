package LintCode.SystemDesign.MapReduce;

import java.util.Iterator;

/**
 * Find top k frequent words with map reduce framework.
 * 
 * The mapper's key is the document id, value is the content of the document, words in a document are split by spaces.
 * 
 * For reducer, the output should be at most k key-value pairs, which are the top k words and their frequencies in this
 * reducer. The judge will take care about how to merge different reducers' results to get the global top k frequent
 * words, so you don't need to care about that part.
 * 
 * The k is given in the constructor of TopK class.
 */
public class TopKFrequentWords {

    public static class Map {
        public void map(String _, Document value, OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
        }
    }

    public static class Reduce {

        public void setup(int k) {
            // initialize your data structure here
        }

        public void reduce(String key, Iterator<Integer> values) {
            // Write your code here
        }

        public void cleanup(MRWordCount.OutputCollector<String, Integer> output) {
            // Output the top k pairs <word, times> into output buffer.
            // Ps. output.collect(String key, Integer value);
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
