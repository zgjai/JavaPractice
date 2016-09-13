package practice.BiasedLockTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangguijiang on 16/7/7.
 */
public class TestBiasedLock {
    public static void main(String[] args) {
        TestBiasedLock test = new TestBiasedLock();
        for (int i = 1; i < 10; i++) {
            test.test(i);
        }
    }

    private void test(int threadNum) {
        long allTime = 0;

        System.out.println("运行的线程数量为" + threadNum);
        for (int count = 0; count < 10; count++) {
            long tsStart = System.currentTimeMillis();

            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadNum, 10, 0, TimeUnit.SECONDS,
                    new LinkedBlockingDeque<Runnable>());
            BiasedLockThread.setList(new Vector<Integer>());

            List<Future> resultList = new ArrayList<Future>();
            for (int i = 1; i <= threadNum; i++) {
                Future result = threadPoolExecutor.submit(new BiasedLockThread());
                resultList.add(result);
            }
            threadPoolExecutor.shutdown();

            try {
                for (int i = 0; i < threadNum; i++) {
                    resultList.get(i).get();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            allTime += System.currentTimeMillis() - tsStart;
        }

        System.out.println("一共耗费：" + (allTime / 10) + " ms");
    }
}
