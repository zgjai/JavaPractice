package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/25.
 */

/**
 * Reverse Linked List A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

public class ReverseLinkedList206 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        //return reverseIteratively(head);
        return reverseRecursively(head);
    }

    private ListNode reverseIteratively(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode reverseRecursively(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseRecursively(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
