package practice.HotSpot.ClassStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangguijiang on 2016/11/21.
 */
public class FieldStructure {

    public String publicString = "publicStringValue";
    private String privateString = "privateStringValue";


    private static final int privateFinalStaticInt = 0x33;
    private static final Integer privateFinalStaticInteger = 0x33;
    private static final String privateFinalStaticString = "privateFinalStaticStringValue";
    private static String privateStaticString = "privateStaticStringValue";
    private final String privateFinalString = "privateFinalStringValue";


    public Integer publicInteger = 0x33;
    private int privateInt = 0x33;
    private short privateShort = 0x33;
    private char privateChar = 0x33;
    private final Long privateFinalLong = 0x33L;
    public final long publicFinalNativeLong = 0x33L;


    private int[] intArray = { 1, 2, 3, 4, 0x33, 0x20 };
    private String[] strArray = { "publicStringValue", "privateStringValue" };
    static private String[] staticStrArray = { "publicStringValue", "privateStringValue" };


    private static final List<String> privateStaticFinalStringList = new ArrayList<String>();
    public static Map<String, String> publicStaticMap = new HashMap<String, String>();


    static {
        privateStaticFinalStringList.add("first");
        privateStaticFinalStringList.add("second");
        privateStaticFinalStringList.add("third");

        publicStaticMap.put("1", "firstValue");
        publicStaticMap.put("2", "secondValue");
    }

}
