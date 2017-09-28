package LeetCode.LinkedList;

/**
 * Created by zhangguijiang on 2017/9/28.
 */

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the
 * list or null.
 * 
 * Return a deep copy of the list.
 */
public class CopyListWithRandomPointer138 {
    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode curr = head;
        while (curr != null) {
            RandomListNode copy = new RandomListNode(curr.label);
            copy.next = curr.next;
            copy.random = curr.random;
            curr.next = copy;
            curr = copy.next;
        }

        curr = head;
        while (curr != null) {
            curr = curr.next;
            if (curr.random != null) {
                curr.random = curr.random.next;
            }
            curr = curr.next;
        }

        RandomListNode dummy = new RandomListNode(0);
        curr = head;
        RandomListNode copyCurr = dummy;
        while (curr != null) {
            copyCurr.next = curr.next;
            curr.next = copyCurr.next.next;
            curr = curr.next;
            copyCurr = copyCurr.next;
        }
        return dummy.next;
    }
}
