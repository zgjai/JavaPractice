package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/14.
 */

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the
 * original list.
 * 
 * For example, Given 1->2->3->3->4->4->5, return 1->2->5. Given 1->1->1->2->3, return 2->3.
 */
public class RemoveDuplicates82 {

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
        ListNode dummy = null;
        ListNode first = new ListNode(0);
        first.next = head;
        ListNode second = head;
        while (second.next != null) {
            second = second.next;
            if (second.val != first.next.val) {
                if (first.next.next != second) {
                    first.next = second;
                    continue;
                }
                if (dummy == null) {
                    dummy = first.next;
                }
                first = first.next;
            }
        }
        if (dummy == null && first.next == second) {
            dummy = second;
        } else if (first.next != second && second.val == first.next.val) {
            first.next = null;
        }
        return dummy;
    }
}
