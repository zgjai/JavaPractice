package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangguijiang on 16/6/13.
 */
public class QueueTest {
    public static void main(String[] args) {
        QueueTest test = new QueueTest();
        test.test();
    }

    public void test() {
        Queue<Integer> testQueue = new Queue<Integer>();
        testQueue.printQueue();

        for (int i=0; i<10; i++){
            testQueue.enqueue(i);
        }
        testQueue.printQueue();

        for (int i=0; i<5; i++){
            System.out.println(testQueue.dequeue());
        }
        testQueue.printQueue();
    }

    public class Queue<T> {
        private List<T> dataList = new ArrayList<T>();

        public void enqueue(T data) {
            if (data != null) {
                dataList.add(data);
            }
        }

        public T dequeue() {
            if (dataList.size() > 0) {
                T data = dataList.get(0);
                dataList.remove(0);
                return data;
            } else {
                return null;
            }
        }

        // for test
        public void printQueue() {
            if (this.dataList.size() > 0) {
                for (T item : dataList) {
                    System.out.print(item + "  ");
                }
                System.out.print("\n");
            }
        }
    }
}
