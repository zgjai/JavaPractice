package practice.JTool;

/**
 * Created by zhangguijiang on 16/7/13.
 */
public class SynchronizedDeadLock implements Runnable{

    private String threadName;
    private Object lock1;
    private Object lock2;

    public SynchronizedDeadLock(String threadName, Object lock1, Object lock2) {
        this.threadName = threadName;
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    public void run() {
        try {
            synchronized (lock1){
                System.out.println(threadName + " get lock" + lock1.toString());
                Thread.sleep(3000);
                synchronized (lock2){
                    System.out.println(threadName + " get lock" + lock2.toString());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
