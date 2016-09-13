package practice;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by zhangguijiang on 16/5/30.
 */
public class StackTest {

    public static void main(String[] args) {
        StackTest test = new StackTest();
        test.stackTest();
    }

    private void stackTest() {
        Stack<Integer> stack = new Stack<Integer>();
        TestThread thread1 = new TestThread(stack);
        TestThread thread2 = new TestThread(stack);
        TestThread thread3 = new TestThread(stack);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class TestThread extends Thread {
        private Stack stack;

        public TestThread(Stack stack) {
            this.stack = stack;
        }

        public void run() {
            Random random = new Random();
            int i = 5;
            while (i > 0) {
                i--;
                int num = random.nextInt(100);
                System.out.println("push value: " + num);
                stack.push(num);
            }
        }
    }

    private class Node<T> {
        Node<T> next;
        T value;

        public Node(Node<T> next, T value) {
            this.next = next;
            this.value = value;
        }
    }

    private class Stack<T> {
        AtomicReference<Node<T>> top = new AtomicReference<Node<T>>();

        public void push(T value) {
            boolean flag = false;
            Node<T> oldTop = null;
            Node<T> newTop = null;
            while (!flag) {
                oldTop = this.top.get();
                newTop = new Node<T>(oldTop, value);
                flag = this.top.compareAndSet(oldTop, newTop);
            }
        }

        public T pop() {
            boolean flag = false;
            Node<T> oldTop = null;
            Node<T> newTop = null;
            while (!flag) {
                oldTop = this.top.get();
                if (oldTop != null) {
                    newTop = oldTop.next;
                    flag = this.top.compareAndSet(oldTop, newTop);
                } else {
                    return null;
                }
            }
            return oldTop.value;
        }

        public synchronized T peek() {
            T topValue = this.top.get().value;
            System.out.println("top value: " + topValue);
            return topValue;
        }
    }
}
