package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/29.
 */

import java.util.Random;

/**
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same
 * probability of being chosen.
 * 
 * Follow up: What if the linked list is extremely large and its length is unknown to you? Could you solve this
 * efficiently without using extra space?
 * 
 * Example:
 * 
 * // Init a singly linked list [1,2,3]. ListNode head = new ListNode(1); head.next = new ListNode(2); head.next.next =
 * new ListNode(3); Solution solution = new Solution(head);
 * 
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 * solution.getRandom();
 */

/**
 * 解题思路：
 * 池塘采样算法
 */
public class LinkedListRandomNode382 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    private ListNode head;

    public LinkedListRandomNode382(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        int[] result = random(head, 1);
        if (result == null) {
            return 0;
        }
        return result[0];
    }

    // 池塘采样算法
    private int[] random(ListNode head, int num) {
        int[] result = new int[num];
        int i = 1;
        for (i = 1; i <= num; i++) {
            if (head == null) {
                return null;
            }
            result[i - 1] = head.val;
            head = head.next;
        }
        Random random = new Random();
        while (head != null) {
            if (isReplace(num, i)) {
                result[random.nextInt(num)] = head.val;
            }
            head = head.next;
            i++;
        }
        return result;
    }

    private boolean isReplace(int k, int i) {
        Random random = new Random();
        return random.nextInt(i) < k;
    }
}
