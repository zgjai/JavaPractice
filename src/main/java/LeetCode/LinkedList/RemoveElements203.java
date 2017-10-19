package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/13.
 */

/**
 * Remove all elements from a linked list of integers that have value val.
 * 
 * Example Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 * Return: 1 --> 2 --> 3 --> 4 --> 5
 */

/**
 * 解题思路：
 * 注意头节点可能会被删除
 */
public class RemoveElements203 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = null;
        ListNode iterator = new ListNode(0);
        iterator.next = head;
        while (iterator.next != null) {
            if (iterator.next.val == val) {
                iterator.next = iterator.next.next;
                continue;
            }
            if (dummy == null) {
                dummy = iterator.next;
            }
            iterator = iterator.next;
        }
        return dummy;
    }
}
