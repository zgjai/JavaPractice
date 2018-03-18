package practice.JvmPractice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangguijiang on 2018/2/22.
 */
public class GcSleepTest {
    private static final Logger logger = LoggerFactory.getLogger(GcSleepTest.class);

    static class GcObject {
        private String s;

        GcObject(String s) {
            this.s = s;
        }

        @Override
        protected void finalize() throws Throwable {
            logger.info("before sleep system time={}", System.currentTimeMillis());
            logger.info("before sleep nano time={}", System.nanoTime());
            Thread.sleep(10000);
            logger.info("after sleep system time={}", System.currentTimeMillis());
            logger.info("after sleep nano time={}", System.nanoTime());
        }
    }

    public static void main(String[] args) throws Throwable {
        GcObject gcObject = new GcObject("test");
        logger.info("before gc system time={}", System.currentTimeMillis());
        logger.info("before gc nano time={}", System.nanoTime());
        System.gc();
        logger.info("after gc system time={}", System.currentTimeMillis());
        logger.info("after gc time={}", System.nanoTime());
        gcObject = null;
        System.gc();
        Thread.sleep(20000);
        logger.info("system time={}", System.currentTimeMillis());
        logger.info("nano time={}", System.nanoTime());
    }
}
