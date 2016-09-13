package practice.StampedLockTest;

import java.util.concurrent.locks.StampedLock;

/**
 * Created by zhangguijiang on 16/7/14.
 */
public class StampedLockReadThread implements Runnable {

    private StampedLock lock;
    private Long num;

    public StampedLockReadThread(StampedLock lock, Long num) {
        this.lock = lock;
        this.num = num;
    }

    public void run() {
        long nowNum = 0;
        while (nowNum < 100000) {
            long stamp = lock.tryOptimisticRead();
            nowNum = this.num;
            if (!lock.validate(stamp)) {
                stamp = lock.readLock();
                try {
                    nowNum = this.num;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlockRead(stamp);
                }
            }
        }
    }
}
