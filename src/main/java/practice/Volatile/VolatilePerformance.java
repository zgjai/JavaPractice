package practice.Volatile;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by zhangguijiang on 2017/7/21.
 */
public class VolatilePerformance {
    private static int i = 1000;
    private static volatile int vi = 1000;

    public static void main(String[] args) {
        List<Integer> intList = Lists.newArrayList();
        long curTime = System.currentTimeMillis();
        for (int r = 0; r < 10; r++) {
            for (int j = 0; j < 10000000; j++) {
                int k = vi;
                intList.add(k);
            }
        }
        System.out.println(System.currentTimeMillis() - curTime);
        System.out.println(intList.size());
    }
}
