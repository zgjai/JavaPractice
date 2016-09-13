package practice;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zhangguijiang on 16/6/6.
 */
public class QueueCompare {
    public static void main(String[] args){
        QueueCompare test = new QueueCompare();
        test.test();
    }

    public void test(){
        Queue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<Integer>();
        Queue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<Integer>();

        //测试并发写  concurrentLinkedQueue
        Thread thread1 = new QueueTestThread(concurrentLinkedQueue, 0);
        Thread thread2 = new QueueTestThread(concurrentLinkedQueue, 0);
        Thread thread3 = new QueueTestThread(concurrentLinkedQueue, 0);
        Thread thread4 = new QueueTestThread(concurrentLinkedQueue, 0);
        Thread thread5 = new QueueTestThread(concurrentLinkedQueue, 0);

        long startTime = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - startTime;
        System.out.println("concurrentLinkedQueue time=" + time);

        //测试并发写   linkedBlockingQueue
        thread1 = new QueueTestThread(linkedBlockingQueue, 0);
        thread2 = new QueueTestThread(linkedBlockingQueue, 0);
        thread3 = new QueueTestThread(linkedBlockingQueue, 0);
        thread4 = new QueueTestThread(linkedBlockingQueue, 0);
        thread5 = new QueueTestThread(linkedBlockingQueue, 0);

        startTime = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        time = System.currentTimeMillis() - startTime;
        System.out.println("linkedBlockingQueue time=" + time);


        //测试并发取  concurrentLinkedQueue
        thread1 = new QueueTestThread(concurrentLinkedQueue, 1);
        thread2 = new QueueTestThread(concurrentLinkedQueue, 1);
        thread3 = new QueueTestThread(concurrentLinkedQueue, 1);
        thread4 = new QueueTestThread(concurrentLinkedQueue, 1);
        thread5 = new QueueTestThread(concurrentLinkedQueue, 1);

        startTime = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        time = System.currentTimeMillis() - startTime;
        System.out.println("concurrentLinkedQueue time=" + time);


        //测试并发取  linkedBlockingQueue
        thread1 = new QueueTestThread(linkedBlockingQueue, 1);
        thread2 = new QueueTestThread(linkedBlockingQueue, 1);
        thread3 = new QueueTestThread(linkedBlockingQueue, 1);
        thread4 = new QueueTestThread(linkedBlockingQueue, 1);
        thread5 = new QueueTestThread(linkedBlockingQueue, 1);

        startTime = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        time = System.currentTimeMillis() - startTime;
        System.out.println("linkedBlockingQueue time=" + time);
    }

    private class QueueTestThread extends Thread {
        private Queue<Integer> queue;
        private int flag;

        public QueueTestThread(Queue<Integer> queue, int flag) {
            this.queue = queue;
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag == 0){
                for (int i=0; i<100000; i++){
                    this.queue.add(i);
                }
            }else {
                for (int i=0; i<100000; i++){
                    this.queue.poll();
                }
            }
        }
    }

}
