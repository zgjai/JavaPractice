package practice.StampedLockTest;

import java.util.concurrent.locks.StampedLock;

/**
 * Created by zhangguijiang on 16/7/14.
 */
public class StampedLockWriteThread implements Runnable {

    private StampedLock lock;
    private Long num;

    public StampedLockWriteThread(StampedLock lock, Long num) {
        this.lock = lock;
        this.num = num;
    }

    public void run() {
        long nowNum = 0;
        while (nowNum < 100000) {
            long stamp = lock.writeLock();
            try {
                if (num <= 1000000) {
                    nowNum = ++this.num;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlockWrite(stamp);
            }
        }
        System.out.println(nowNum);
    }
}
