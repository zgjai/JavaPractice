package practice.JavaGC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangguijiang on 16/8/6.
 */
public class LogThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(LogThread.class);

    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                logger.info("log");
                logger.info("maxMem:{}M", Runtime.getRuntime().maxMemory() / 1024.0 / 2014);
                logger.info("freeMem:{}M", Runtime.getRuntime().freeMemory() / 1024.0 / 2014);
                logger.info("totalMem:{}M", Runtime.getRuntime().totalMemory() / 1024.0 / 2014);
            }
        } catch (Exception e) {
            logger.error("run err", e);
        }
    }
}
