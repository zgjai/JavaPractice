package practice.JavaGC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangguijiang on 16/8/6.
 */
public class GCThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(GCThread.class);
    byte[][] a = new byte[200][];

    public void run() {
        for (int i = 0; i < 200; i++) {
            a[i] = new byte[1 * 1024 * 1024];
            try {
                Thread.sleep(50);
            }catch (Exception e){
                logger.error("sleep err", e);
            }
        }
        logger.info("done");

        a = null;
        logger.info("gc");
        System.gc();
    }
}
