package practice.HotSpot.ClassStructure;

import java.io.IOException;

/**
 * Created by zhangguijiang on 2016/11/20.
 */
public class FunctionStructure {

    public void returnInt(long nativeLongParam, String strParam, short intParam, boolean bool, Long longParam) {
    }

    public String returnStr(int i, char c) {
        String stringToReturn = "";
        for (int j = 0; j < i; j++) {
            stringToReturn = stringToReturn + j;
        }
        return stringToReturn;
    }

    public int exceptionTest() throws Exception {
        int i;
        try {
            i = 1;
            return i;
        } catch (Exception e) {
            i = 2;
            return i;
        } finally {
            i = 3;
            return i;
        }
    }
}
