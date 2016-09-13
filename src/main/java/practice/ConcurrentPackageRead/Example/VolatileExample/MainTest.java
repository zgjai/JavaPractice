package practice.ConcurrentPackageRead.Example.VolatileExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangguijiang on 16/7/25.
 */
public class MainTest {

    private static final Logger logger = LoggerFactory.getLogger(MainTest.class);

    public static void main(String[] args){
        while (true){
            WriteThread.setCount(0);
            for (int i=0; i<3; i++){
                new Thread(new WriteThread()).start();
            }

            try {
                Thread.sleep(1000);
            }catch (Exception e){
                logger.error("err");
            }

            int count = WriteThread.getCount();
            if (count != 3000){
                logger.info("count now is {}", count);
                break;
            }
        }
    }
}
