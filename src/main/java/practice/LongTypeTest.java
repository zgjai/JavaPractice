package practice;

/**
 * Created by zhangguijiang on 16/5/25.
 */
public class LongTypeTest implements Runnable{
    private static long test = 0;

    private final long val;

    public LongTypeTest(long val) {
        this.val = val;
    }

    public void run() {
        while (!Thread.interrupted()) {
            test = val;
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new LongTypeTest(-1));
        Thread t2 = new Thread(new LongTypeTest(0));

        System.out.println(Long.toBinaryString(-1));
        System.out.println(pad(Long.toBinaryString(0), 64));

        t1.start();
        t2.start();

        long val;
        while ((val = test) == -1 || val == 0) {
            continue;
        }

        System.out.println(pad(Long.toBinaryString(val), 64));
        System.out.println(val);

        t1.interrupt();
        t2.interrupt();
    }

    private static String pad(String s, int targetLength) {
        int n = targetLength - s.length();
        for (int x = 0; x < n; x++) {
            s = "0" + s;
        }
        return s;
    }
}
