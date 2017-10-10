package LeetCode.Stack;

/**
 * Created by zhangguijiang on 2017/10/10.
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not
 * the kth distinct element.
 * 
 * For example, Given [3,2,1,5,6,4] and k = 2, return 5.
 */
public class KthLargestElement215 {
    public int findKthLargest(int[] nums, int k) {
        MinHeap<Integer> minHeap = new MinHeap<>(k);
        for (int num : nums) {
            minHeap.add(num);
        }
        return minHeap.getMin();
    }

    private class MinHeap<T extends Comparable<T>> {
        private Queue<T> queue;
        private int maxSize;

        public MinHeap(int maxSize) {
            if (maxSize <= 0) {
                throw new IllegalArgumentException();
            }
            this.maxSize = maxSize;
            this.queue = new PriorityQueue<>(maxSize, Comparator.naturalOrder());
        }

        public void add(T element) {
            if (queue.size() < maxSize) {
                queue.add(element);
            } else {
                T min = queue.peek();
                if (element.compareTo(min) > 0) {
                    queue.poll();
                    queue.add(element);
                }
            }
        }

        public T getMin() {
            if (queue.isEmpty()) {
                return null;
            }
            return queue.peek();
        }
    }
}
