package practice.TestPackage;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangguijiang on 16/8/15.
 */
public class ArrayLengthTest {

    public int[] array = new int[18];
    public String string = "a";

    public static void main(String[] args){
        final Logger logger = LoggerFactory.getLogger(ArrayLengthTest.class);
        ArrayLengthTest test = new ArrayLengthTest();
        logger.info("start");
        logger.info(VM.current().details());
        logger.info(ClassLayout.parseClass(Integer.class).toPrintable());
        logger.info(ClassLayout.parseClass(int.class).toPrintable());
        logger.info(ClassLayout.parseClass(int[].class).toPrintable());
        logger.info("second");
        logger.info(GraphLayout.parseInstance(test).toPrintable());
    }
}
