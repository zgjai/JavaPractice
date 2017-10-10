package LeetCode.Stack;

/**
 * Created by zhangguijiang on 2017/10/9.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Implement the following operations of a queue using stacks.
 * 
 * push(x) -- Push element x to the back of queue. pop() -- Removes the element from in front of queue. peek() -- Get
 * the front element. empty() -- Return whether the queue is empty.
 * 
 * Notes: You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and
 * is empty operations are valid. Depending on your language, stack may not be supported natively. You may simulate a
 * stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack. You may
 * assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 */
public class ImplementQueueUsingStacks232 {
    private Stack<Integer> stack;
    private Stack<Integer> backStack;

    public ImplementQueueUsingStacks232() {
        stack = new Stack<>();
        backStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
       stack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        int x = 0;
        while (!stack.isEmpty()) {
            x = stack.pop();
            if (stack.isEmpty()) {
                break;
            }
            backStack.push(x);
        }
        while (!backStack.isEmpty()) {
            stack.push(backStack.pop());
        }
        return x;
    }

    /** Get the front element. */
    public int peek() {
        int x = 0;
        while (!stack.isEmpty()) {
            x = stack.pop();
            backStack.push(x);
        }
        while (!backStack.isEmpty()) {
            stack.push(backStack.pop());
        }
        return x;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }

    private class Stack<T> {
        private List<T> list;
        private int index;

        public Stack() {
            list = new ArrayList<>();
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
            T element = list.remove(index);
            index--;
            return element;
        }

        public T top() {
            return list.get(index);
        }

        public boolean isEmpty() {
            return index < 0;
        }
    }
}
