package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/28.
 */

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking
 * about the node number and not the value in the nodes.
 * 
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * 
 * Example: Given 1->2->3->4->5->NULL, return 1->3->5->2->4->NULL.
 * 
 * Note: The relative order inside both the even and odd groups should remain as it was in the input. The first node is
 * considered odd, the second node even and so on ...
 */

/**
 * 解题思路：
 * 采用两个指针，分别作为奇偶链表的哨兵节点，在遍历原始链表的过程中，交替扩展奇偶链表
 */

public class OddEvenList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode odd = new ListNode(0), currOdd = odd, curr = head;
        ListNode even = new ListNode(0), currEven = even;
        odd.next = head;
        while (curr != null && curr.next != null) {
            currOdd.next = curr;
            currOdd = currOdd.next;
            curr = curr.next;
            currEven.next = curr;
            currEven = currEven.next;
            curr = curr.next;
        }
        if (curr != null) {
            currOdd.next = curr;
            currOdd = currOdd.next;
            currEven.next = null;
        }
        currOdd.next = even.next;
        return odd.next;
    }
}
