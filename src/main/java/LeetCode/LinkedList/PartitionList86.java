package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/27.
 *
 */

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or
 * equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * 
 * For example, Given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
 */
public class PartitionList86 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode smallDummy = new ListNode(0), smallCurr = smallDummy;
        ListNode largeDummy = new ListNode(0), largeCurr = largeDummy;
        while (head != null) {
            if (head.val < x) {
                smallCurr.next = head;
                smallCurr = head;
            } else {
                largeCurr.next = head;
                largeCurr = head;
            }
            head = head.next;
        }
        smallCurr.next = largeDummy.next;
        largeCurr.next = null;
        return smallDummy.next;
    }
}
