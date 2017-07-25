package practice.Volatile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangguijiang on 2017/7/11.
 */
public class VolatileTest {

    private static int unsafeNum = 0;
    //private static volatile int safeNum = 0;

    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread(VolatileTest::increase);
        Thread thread2 = new Thread(VolatileTest::increase);
        Thread thread3 = new Thread(VolatileTest::increase);
        Thread thread4 = new Thread(VolatileTest::increase);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join(50);
        thread2.join(50);
        thread3.join(50);
        thread4.join(50);
        //System.out.println("safeNum:" + safeNum);
        System.out.println("unsafeNum:" + unsafeNum);
    }

    private static void increase() {
        for (int i = 0; i < 100000; i++) {
            //safeNum++;
            unsafeNum++;
        }
    }
}
