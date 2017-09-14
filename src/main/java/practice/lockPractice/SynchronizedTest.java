package practice.lockPractice;

/**
 * Created by zhangguijiang on 2017/7/28.
 */
public class SynchronizedTest {

    private static final Object slock = new Object();
    private final Object lock = new Object();

    private static int num = 0;

    public static void main(String[] args) throws Exception {
        System.out.println("num: " + num);
        Thread addThread1 = new Thread(SynchronizedTest::increase, "add-1");
        Thread addThread2 = new Thread(SynchronizedTest::increase, "add-2");
        addThread1.start();
        addThread2.start();
        addThread1.join();
        addThread2.join();
        System.out.println("num: " + num);

        Thread minusT1 = new Thread(SynchronizedTest::decrease2, "minus-1");
        Thread minusT2 = new Thread(SynchronizedTest::decrease2, "minus-2");
        minusT1.start();
        minusT2.start();
        minusT1.join();
        minusT2.join();
        System.out.println("num: " + num);

        SynchronizedTest test = new SynchronizedTest();
        Thread minusT3 = new Thread(test::decrease1, "minus-3");
        Thread minusT4 = new Thread(test::decrease1, "minus-4");
        minusT3.start();
        minusT4.start();
        minusT3.join();
        minusT4.join();
        System.out.println("num: " + num);
    }

    private static synchronized void increase() {
        for (int i = 0; i < 100; i++) {
            num++;
        }
    }

    private static void decrease2() {
        for (int i = 0; i < 100; i++) {
            num--;
        }
    }

    private void decrease1() {
        synchronized (lock) {
            for (int i = 0; i < 100; i++) {
                num--;
            }
        }
    }
}
