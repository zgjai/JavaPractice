package practice.JTool;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangguijiang on 16/7/14.
 */
public class ReentrantLockDeadLock implements Runnable {

    private String threadName;
    private ReentrantLock lock1;
    private ReentrantLock lock2;

    public ReentrantLockDeadLock(String threadName, ReentrantLock lock1, ReentrantLock lock2) {
        this.threadName = threadName;
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    public void run() {
        try {
            lock1.lock();
            System.out.println(threadName + " get lock" + lock1);
            Thread.sleep(3000);
            try {
                lock2.lock();
                System.out.println(threadName + " get lock" + lock2);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock2.unlock();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock1.unlock();
        }
    }
}
