package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/13.
 */

/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * 
 * For example,
 * 
 * Given linked list: 1->2->3->4->5, and n = 2. After removing the second node from the end, the linked list becomes
 * 1->2->3->5.
 * 
 * Note: Given n will always be valid. Try to do this in one pass.
 */
public class RemoveNthNode19 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = new ListNode(0);
        first.next = head;
        ListNode dummy = first;
        ListNode second = head;
        // 通过前后指针实现
        // 前后指针之间相隔n的距离，当后指针到达链表尾时，删除前指针的下个节点即可
        for (; n > 1; n--) {
            if (second.next == null) {
                return head;
            }
            second = second.next;
        }
        //
        while (second.next != null) {
            first = first.next;
            second = second.next;
        }
        first.next = first.next.next;
        if (dummy == first) {
            return dummy.next;
        }
        return head;
    }
}
