package practice.lockPractice;

/**
 * Created by zhangguijiang on 2017/9/7.
 */
public class StampedLock {

    static final class WNode {
        volatile WNode prev;
        volatile WNode next;
        volatile WNode cowait;
        volatile Thread thread;
        volatile int status;
        final int mode;

        WNode(int m, WNode p) {
            mode = m;
            prev = p;
        }
    }
}
