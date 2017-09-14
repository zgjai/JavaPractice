package practice.lockPractice;

/**
 * Created by zhangguijiang on 2017/7/27.
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {
        ArrayBuffer<Integer> buffer = new ArrayBuffer<>(5);
        Thread t1 = new Thread(() -> put(buffer), "put-1");
        Thread t2 = new Thread(() -> put(buffer), "put-2");
        Thread t3 = new Thread(() -> {
            while (true) {
                buffer.take();
            }
        }, "take-1");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
    }

    private static void put(ArrayBuffer<Integer> buffer) {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(100);
                buffer.put(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
