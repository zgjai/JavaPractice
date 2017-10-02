package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/30.
 */

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse
 * order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8
 */
public class AddTwoNumbers2 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            ListNode tmpNode = new ListNode(carry);
            curr.next = tmpNode;
            curr = curr.next;
            if (l1 != null) {
                tmpNode.val = tmpNode.val + l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                tmpNode.val = tmpNode.val + l2.val;
                l2 = l2.next;
            }
            carry = tmpNode.val / 10;
            tmpNode.val = tmpNode.val % 10;
        }
        if (carry != 0) {
            curr.next = new ListNode(carry);
        }
        return dummy.next;
    }
}
