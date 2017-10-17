package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/28.
 */

import java.util.List;

/**
 * Given a linked list, determine if it has a cycle in it.
 * 
 * Follow up: Can you solve it without using extra space?
 */

/**
 * 解题思路：
 * 快慢指针法，如果两个指针有交汇，说明有环
 */
public class LinkedListCycle {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if (slow == fast || slow == fast.next) {
                return true;
            }
            fast = fast.next;
        }
        return false;
    }
}
