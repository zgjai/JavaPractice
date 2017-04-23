package practice;

/**
 * Created by zhangguijiang on 2017/1/9.
 */
public class ThreadTest {
    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        testThread.start();
        System.out.println("main end");
    }

    private static class TestThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("thread end");
        }
    }
}
