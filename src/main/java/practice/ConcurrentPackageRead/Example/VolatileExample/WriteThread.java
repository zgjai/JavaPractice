package practice.ConcurrentPackageRead.Example.VolatileExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangguijiang on 16/7/25.
 */
public class WriteThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(WriteThread.class);
    private static Object object = new Object();

    private static int count = 0;

    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {
                inc();
            }
            //logger.info("done");
        } catch (Exception e) {
            logger.error("inc err", e);
        }
    }

    private void inc() {
        synchronized (object) {
            count++;
        }
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        WriteThread.count = count;
    }
}
