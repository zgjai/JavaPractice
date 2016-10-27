package practice.HotSpot.ClassStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangguijiang on 2016/10/13.
 */
public class ClassStructureTest implements ClassInterface {
    private static final String privateFinalStaticString = "privateFinalStaticStringValue";
    private static String privateStaticString = "privateStaticStringValue";
    private final String privateFinalString = "privateFinalStringValue";
    public String publicString = "publicStringValue";
    private String privateString = "privateStringValue";
    public Integer publicInteger = 0x33;
    private int privateInt = 0x44;
    private final Long privateFinalLong = 0x1111L;
    public final long publicFinalNativeLong = 0x2222L;
    private static final List<String> privateStaticFinalStringList = new ArrayList<String>();
    public static Map<String, String> publicStaticMap = new HashMap<String, String>();
    public static final int num = 2;

    static {
        privateStaticFinalStringList.add("first");
        privateStaticFinalStringList.add("second");
        privateStaticFinalStringList.add("third");

        publicStaticMap.put("1", "firstValue");
        publicStaticMap.put("2", "secondValue");
    }

    public static void staticFunction() {
        System.out.print("staticFunction");
    }

    public void function() {
        System.out.print("function");
    }

    public void testInterface() {

    }

    public int returnInt() {
        return 0;
    }

}
