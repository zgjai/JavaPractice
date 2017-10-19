package LeetCode.LinkedList;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or
 * equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * 
 * For example, Given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
 */

/**
 * 解题思路：
 * 采用双指针，分别作为小于x跟大于等于x的链表的哨兵节点，遍历原始链表构建两个新链表，最后合并
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
