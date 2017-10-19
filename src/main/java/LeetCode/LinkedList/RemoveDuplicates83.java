package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/14.
 */

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * 
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 */

/**
 * 解题思路：
 * 采用双指针
 */
public class RemoveDuplicates83 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = head;
        while (second.next != null) {
            second = second.next;
            if (second.val == first.val) {
                continue;
            }
            first.next = second;
            first = second;
        }
        if (second.val == first.val) {
            first.next = null;
        }
        return head;
    }
}
