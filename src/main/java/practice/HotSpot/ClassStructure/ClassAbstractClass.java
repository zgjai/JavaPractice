package practice.HotSpot.ClassStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangguijiang on 2016/10/26.
 */
public abstract class ClassAbstractClass {

    private String privateStr = "privateStrValue";
    public static final Integer publicStaticFinal = 0x11;

    public abstract String abstractMethod();

    private static List privateStaticList(int listSize){
        List<String> stringList = new ArrayList<String>(listSize);
        for (int i=0; i<listSize; i++){
            stringList.add("i" + i);
        }
        return stringList;
    }
}
