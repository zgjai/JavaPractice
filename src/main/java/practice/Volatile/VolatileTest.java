package practice.Volatile;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Vector;

/**
 * Created by zhangguijiang on 2017/7/11.
 */
public class VolatileTest {

    private static int unsafeNum = 0;
    private static volatile int safeNum = 0;

    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread(VolatileTest::increase);
        Thread thread2 = new Thread(VolatileTest::increase);
        Thread thread3 = new Thread(VolatileTest::increase);
        Thread thread4 = new Thread(VolatileTest::increase);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        System.out.println("safeNum: " + safeNum);
        System.out.println("unsafeNum: " + unsafeNum);
    }

    private static void increase() {
        for (int i = 0; i < 10000; i++) {
            int tmpUnsafe = unsafeNum + 1;
            int tmpSafe = safeNum + 1;
            sleep(1);
            safeNum = tmpSafe;
            unsafeNum = tmpUnsafe;
        }
    }

     // private static void increase() { for (int i = 0; i < 10000; i++) { unsafeNum++; safeNum++; sleep(1); } }

    private static void sleep(int m) {
        try {
            Thread.sleep(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
