package practice.BiasedLockTest;

import java.util.List;

/**
 * Created by zhangguijiang on 16/7/7.
 */
public class BiasedLockThread implements Runnable {

    private static List<Integer> list;

    public static void setList(List<Integer> list) {
        BiasedLockThread.list = list;
    }

    public void run() {
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
    }
}
