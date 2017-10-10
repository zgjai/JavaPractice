package LeetCode.Stack;

/**
 * Created by zhangguijiang on 2017/10/10.
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So
 * the median is the mean of the two middle value.
 * 
 * Examples: [2,3,4] , the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Design a data structure that supports the following two operations:
 * 
 * void addNum(int num) - Add a integer number from the data stream to the data structure. double findMedian() - Return
 * the median of all elements so far.
 */
public class FindMedianFromDataStream295 {

    private Queue<Integer> smallHeap = new PriorityQueue<>(Comparator.naturalOrder());
    private Queue<Integer> bigHeap = new PriorityQueue<>(Comparator.reverseOrder());

    public void addNum(int num) {
        if (bigHeap.isEmpty()) {
            bigHeap.add(num);
            return;
        }
        if (bigHeap.size() == smallHeap.size()) {
            if (num < bigHeap.peek()) {
                bigHeap.add(num);
            } else {
                smallHeap.add(num);
            }
        } else if (bigHeap.size() > smallHeap.size()) {
            if (num > bigHeap.peek()) {
                smallHeap.add(num);
            } else {
                smallHeap.add(bigHeap.poll());
                bigHeap.add(num);
            }
        } else if (bigHeap.size() < smallHeap.size()) {
            if (num < smallHeap.peek()) {
                bigHeap.add(num);
            } else {
                bigHeap.add(smallHeap.poll());
                smallHeap.add(num);
            }
        }
    }

    public double findMedian() {
        if (bigHeap.size() == smallHeap.size()) {
            return (bigHeap.peek() + smallHeap.peek()) / 2.0;
        } else if (bigHeap.size() > smallHeap.size()) {
            return bigHeap.peek();
        }
        return smallHeap.peek();
    }
}
