package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/14.
 */

/**
 * Sort a linked list using insertion sort.
 */
public class InsertionSortList147 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = head;
        ListNode iterator = curr.next;
        head.next = null;
        while (iterator != null) {
            curr = iterator;
            iterator = iterator.next;
            ListNode newListIterator = dummy;
            while (newListIterator.next != null) {
                if (curr.val < newListIterator.next.val) {
                    curr.next = newListIterator.next;
                    newListIterator.next = curr;
                    if (dummy == newListIterator) {
                        dummy.next = curr;
                    }
                    break;
                }
                newListIterator = newListIterator.next;
            }
            if (newListIterator.next == null) {
                newListIterator.next = curr;
                curr.next = null;
            }
        }
        return dummy.next;
    }
}
