package practice.HotSpot.GCTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangguijiang on 2016/9/22.
 */
public class STWTest {
    private static final Logger logger = LoggerFactory.getLogger(STWTest.class);

    private static final int unitM = 1 * 1024 * 1024;

    public static class PrintThread implements Runnable {
        public void run() {
            try {
                while (true) {
                    logger.info("mark by second");
                    Thread.sleep(1);
                }
            }catch (Exception e){
                logger.error("Exception", e);
            }
        }
    }

    private static class AllocateThread implements Runnable {
        public void run() {
            try {
                Map<Integer, byte[]> map = new HashMap<Integer, byte[]>();
                for (int i=0; i<4; i++){
                    map.put(i, new byte[unitM]);
                }
                Thread.sleep(500);

                for (int i=0; i<5; i++){
                    byte[] b = new byte[unitM];
                    b = null;
                }

                byte[] b = new byte[5 * unitM];

                map = null;

                byte[] c = new byte[6 * unitM];
                Thread.sleep(1000);
            }catch (Exception e){
                logger.error("Exception", e);
            }
        }
    }

    public static void main(String[] args){
        new Thread(new PrintThread()).start();
        new Thread(new AllocateThread()).start();
    }
}
