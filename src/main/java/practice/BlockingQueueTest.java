package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhangguijiang on 16/5/23.
 */
public class BlockingQueueTest {

    public static void main(String[] args) {
        BlockingQueueTest test = new BlockingQueueTest();
        test.executor();
    }

    private void executor() {
        BlockingQueue queue = new BlockingQueue();
        TestThread thread1 = new TestThread(queue);
        TestThread thread2 = new TestThread(queue);
        TestThread thread3 = new TestThread(queue);

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private class TestThread extends Thread {

        private BlockingQueue queue;

        public TestThread(BlockingQueue blockingQueue) {
            this.queue = blockingQueue;
        }

        @Override
        public void run() {
            while (true) {
                Random random = new Random();
                int num = random.nextInt(100);
                try {
                    if (num % 2 == 0) {
                        this.queue.get();
                        Thread.sleep(500);
                    } else {
                        this.queue.add(num);
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class BlockingQueue {
        private static final int limit = 10;

        private List<Integer> queue = new ArrayList<Integer>();

        public synchronized Integer get() throws InterruptedException {
            while (this.queue.size() == 0) {
                wait();
            }
            if (this.queue.size() >= limit / 2) {
                notifyAll();
            }
            Integer value = this.queue.get(0);
            this.queue.remove(0);
            System.out.println("get:" + value);
            return value;
        }

        public synchronized void add(Integer value) throws InterruptedException {
            while (this.queue.size() == limit) {
                wait();
            }
            if (this.queue.size() <= limit / 2) {
                notifyAll();
            }
            this.queue.add(value);
            System.out.println("add:" + value);
        }
    }
}
