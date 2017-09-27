package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/27.
 */

/**
 * Given a singly linked list L: L0?L1?…?Ln-1?Ln, reorder it to: L0?Ln?L1?Ln-1?L2?Ln-2?…
 * 
 * You must do this in-place without altering the nodes' values.
 * 
 * For example, Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */
public class ReorderList143 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ReorderList143 reorder = new ReorderList143();
        ListNode head = reorder.stringToListNode("[1,2,3,4,5,6,7]");
        reorder.reorderList(head);
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode fast = head, slow = fast;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode middle = slow.next;
        slow.next = null;
        middle = reverseIteratively(middle);
        head = merge(head, middle);
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

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        dummy.next = l1;
        ListNode curr = l1;
        while (l1 != null && l2 != null) {
            l1 = l1.next;
            curr.next = l2;
            l2 = l2.next;
            curr.next.next = l1;
            curr = l1;
        }
        return dummy.next;
    }

    private String listNodeToString(ListNode node) {
        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return result.substring(0, result.length() - 2);
    }

    private ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    private int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
}
