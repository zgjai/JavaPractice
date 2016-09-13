package practice.ConcurrentPackageRead.Example.VolatileExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangguijiang on 16/7/25.
 */
public class VolatileP {
    private static final Logger logger = LoggerFactory.getLogger(VolatileP.class);

    volatile static int i = 0;
    static int j = 0;

    static int[] test = new int[20];

    public static void main(String[] args){
    test[19] = 0;

        long time = System.currentTimeMillis();
        for (int n=0; n<10000000; n++){
            i = n;
            //int m = i;
        }
        logger.info("time={}", System.currentTimeMillis()-time);
        time = System.currentTimeMillis();
        for (int n=0; n<10000000; n++){
            //test[19] = n;
            //int m = test[19];
            j = n;
        }
        logger.info("time={}", System.currentTimeMillis()-time);
    }
}
