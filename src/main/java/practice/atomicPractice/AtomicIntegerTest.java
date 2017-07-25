package practice.atomicPractice;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangguijiang on 2017/7/25.
 */
public class AtomicIntegerTest {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        new Thread(() -> cas(1, 2), "cas-1").start();
        new Thread(() -> cas(1, 3), "cas-2").start();
        sleep(200);
        System.out.println("cas done...cur v: " + atomicInteger.intValue());

        new Thread(() -> add(1, 3), "add-1").start();
        new Thread(() -> add(2, 3), "add-2").start();
        sleep(200);
        System.out.println("add done...cur v: " + atomicInteger.intValue());
    }

    private static void cas(int except, int update) {
        sleep(50);
        System.out.println(Thread.currentThread().getName() + ": " + atomicInteger.compareAndSet(except, update)
                + " cur value:" + atomicInteger.intValue());
    }

    private static void add(int num, int count) {
        sleep(50);
        for (int i = 1; i < count; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + atomicInteger.addAndGet(num));
        }
    }

    private static void sleep(int m) {
        try {
            Thread.sleep(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
