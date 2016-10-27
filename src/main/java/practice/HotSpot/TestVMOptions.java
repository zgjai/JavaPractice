package practice.HotSpot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by zhangguijiang on 16/9/19.
 */
public class TestVMOptions {
    private static final Logger logger = LoggerFactory.getLogger(TestVMOptions.class);

    public static void main(String[] args){
        try {
            long maxMem = Runtime.getRuntime().maxMemory();
            long freeMem = Runtime.getRuntime().freeMemory();
            long totalMem = Runtime.getRuntime().totalMemory();
            int processors = Runtime.getRuntime().availableProcessors();
            logger.info("maxMem={}, freeMem={}, totalMem={}", maxMem, freeMem, totalMem, processors);
        }catch (Exception e){
            int i = 0;
            logger.error("i={}", i, e);
        }

    }
}
