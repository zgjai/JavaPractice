package practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangguijiang on 16/9/7.
 */
public class FloatToByte {
    private static final Logger logger = LoggerFactory.getLogger(FloatToByte.class);

    public static void main(String[] args) {
        float num = -9.0f;
        logger.info("num={}", Integer.toBinaryString(Float.floatToRawIntBits(num)));
        int first = -99;
        int second = -105;
        int third = 205;
        logger.info("first={}, second={}, third={}", Integer.toBinaryString(first), Integer.toBinaryString(second),
                Integer.toBinaryString(third));
        String str = "c2200000";
        Integer integer = Integer.valueOf(str, 16);
        //str = String.format("%x", str);
        logger.info("str={}", Float.intBitsToFloat(integer));
    }
}
