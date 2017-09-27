package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/27.
 */

/**
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Follow up: Could you do it in O(n) time and O(1) space?
 */
public class Palindrome234 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head, slow = fast;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode middle = slow.next;
        slow.next = null;
        middle = reverseIteratively(middle);
        return compare(head, middle);
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

    private boolean compare(ListNode l1, ListNode l2) {
        while (l2 != null) {
            if (l1.val != l2.val) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }
}
