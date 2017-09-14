package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/12.
 */

/**
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 * 
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should
 * become 1 -> 2 -> 4 after calling your function.
 */
public class DeleteNode {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void deleteNode(ListNode node) {
        // 传统意义上的删除链表中的节点，就是把该节点从链表中移除，但这道题没有办法做到
        // 就使用一个小技巧，将要删除节点的下个节点删除，而使用下个节点的值替代当前节点的值
        ListNode nextNode = node.next;
        node.val = nextNode.val;
        node.next = nextNode.next;
    }
}
