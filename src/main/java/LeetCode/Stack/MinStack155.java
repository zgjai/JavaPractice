package LeetCode.Stack;

/**
 * Created by zhangguijiang on 2017/10/10.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * 
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 */
public class MinStack155 {

    private Integer min = null;
    private Stack<Integer> stack = new Stack<>();

    public void push(int x) {
        if (stack.isEmpty()) {
            min = x;
            stack.push(x);
        } else {
            if (min >= x) {
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }
    }

    public void pop() {
        if (stack.pop().equals(min)) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.top();
    }

    public int getMin() {
        return min;
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
