package LeetCode.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangguijiang on 2018/3/2.
 */
public class MyStack<T> {
    private List<T> list;
    private int index;

    public MyStack() {
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
