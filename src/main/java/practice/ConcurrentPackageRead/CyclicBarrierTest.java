package practice.ConcurrentPackageRead;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by zhangguijiang on 2017/9/12.
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Thread(() -> System.out.println("done")));
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("sleep done");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                    System.out.println("sleep done");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2000);
                    System.out.println("sleep done");
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            Thread.sleep(20000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
