package LeetCode.Stack;

/**
 * Created by zhangguijiang on 2017/10/9.
 */

import scala.Int;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement the following operations of a stack using queues.
 * 
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 *
 * Notes: You must use only standard operations of a queue -- which means
 * only push to back, peek/pop from front, size, and is empty operations are valid. Depending on your language, queue
 * may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you
 * use only standard operations of a queue. You may assume that all operations are valid (for example, no pop or top
 * operations will be called on an empty stack).
 */
public class ImplementStackUsingQueues225 {

    private Queue<Integer> queue;
    private Queue<Integer> backQueue;

    public ImplementStackUsingQueues225() {
        queue = new Queue<>();
        backQueue = new Queue<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.push(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int x = 0;
        while (!queue.isEmpty()) {
            x = queue.pop();
            if (queue.isEmpty()) {
                break;
            }
            backQueue.push(x);
        }
        queue = backQueue;
        backQueue = new Queue<>();
        return x;
    }

    /** Get the top element. */
    public int top() {
        int x = pop();
        queue.push(x);
        return x;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }

    private class Queue<T> {
        private List<T> list;
        private int index;

        public Queue() {
            this.list = new ArrayList<>();
            index = -1;
        }

        public void push(T element) {
            list.add(element);
            index++;
        }

        public T pop() {
            if (isEmpty()) {
                return null;
            }
            T element = list.remove(0);
            index--;
            return element;
        }

        public T peek() {
            return list.get(index);
        }

        public boolean isEmpty() {
            return index < 0;
        }
    }
}
