package practice.HotSpot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangguijiang on 16/8/26.
 */
public class TestHSDB {
    //private static final Logger logger = LoggerFactory.getLogger(TestHSDB.class);

    private String str;
    private Integer integer;
    private int num;
    private int[] intArray;
    private static String staticString;
    private static Integer staticInteger;

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int[] getIntArray() {
        return intArray;
    }

    public void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }

    public static String getStaticString() {
        return staticString;
    }

    public static void setStaticString(String staticString) {
        TestHSDB.staticString = staticString;
    }

    public static Integer getStaticInteger() {
        return staticInteger;
    }

    public static void setStaticInteger(Integer staticInteger) {
        TestHSDB.staticInteger = staticInteger;
    }

    public static void main(String[] args){
        TestHSDB testHSDB = new TestHSDB();
        try {
            int i = 0;
            while (true){
                testHSDB.setInteger(i);
                Thread.sleep(1000);
            }
        }catch (Exception e){
            //logger.error("err", e);
        }
    }
}
