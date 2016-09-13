package practice;

/**
 * Created by zhangguijiang on 16/5/19.
 */
public class ThreeThreadTest {

    private static final String FIRST_THREAD_NAME = "first";
    private static final String SECOND_THREAD_NAME = "second";
    private static final String THIRD_THREAD_NAME = "third";

    public static void main(String[] args) {
        ThreeThreadTest test = new ThreeThreadTest();
        test.executor();
    }

    private void executor() {

        Thread one = new ThreadOne();
        Thread two = new ThreadTwo(one);
        Thread three = new ThreadThree(two);

        three.start();
        one.start();
        two.start();
    }

    private class ThreadOne extends Thread {

        private Thread threadForJoin;

        public ThreadOne() {
            this.setName(FIRST_THREAD_NAME);
        }

        public ThreadOne(Thread threadForJoin) {
            this.setName(FIRST_THREAD_NAME);
            this.threadForJoin = threadForJoin;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread One Start");
                if (this.threadForJoin != null) {
                    System.out.println("Wait For: " + this.threadForJoin.getName());
                    threadForJoin.join();
                    System.out.println("Wait End");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread One End");
        }
    }

    private class ThreadTwo extends Thread {

        private Thread threadForJoin;

        public ThreadTwo() {
            this.setName(SECOND_THREAD_NAME);
        }

        public ThreadTwo(Thread threadForJoin) {
            this.setName(SECOND_THREAD_NAME);
            this.threadForJoin = threadForJoin;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread Two Start");
                if (this.threadForJoin != null) {
                    System.out.println("Wait For: " + this.threadForJoin.getName());
                    threadForJoin.join();
                    System.out.println("Wait End");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread Two End");
        }
    }

    private class ThreadThree extends Thread {

        private Thread threadForJoin;

        public ThreadThree() {
            this.setName(THIRD_THREAD_NAME);
        }

        public ThreadThree(Thread threadForJoin) {
            this.setName(THIRD_THREAD_NAME);
            this.threadForJoin = threadForJoin;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread Three Start");
                if (this.threadForJoin != null) {
                    System.out.println("Wait For: " + this.threadForJoin.getName());
                    threadForJoin.join();
                    System.out.println("Wait End");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread Three End");
        }
    }
}
