package practice.ConcurrentPackageRead.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangguijiang on 2017/9/28.
 */
public class Executors_1 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyRunnable("" + (i + 1)));
        }
        Thread.sleep(2000);
        System.out.println("");
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> Thread.currentThread().interrupt());
        }
        for (int i = 5; i < 10; i++) {
            executorService.execute(new MyRunnable("" + (i + 1)));
        }
    }

    public static class MyRunnable implements Runnable {
        private String username;

        public MyRunnable(String username) {
            this.username = username;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " username=" + username + " begin "
                        + System.currentTimeMillis());
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " username=" + username + " end "
                        + System.currentTimeMillis());
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
