package practice.StampedLockTest;

import java.util.concurrent.locks.StampedLock;

/**
 * Created by zhangguijiang on 16/7/14.
 */
public class StampedLockTest {
    public static void main(String[] args) {
        StampedLock lock = new StampedLock();
        Long num = new Long(0);
        long currentTime = System.currentTimeMillis();


        for (int i = 0; i < 20; i++) {
            Thread readThread = new Thread(new StampedLockReadThread(lock, num));
            readThread.start();
        }
        Thread writeThread = new Thread(new StampedLockWriteThread(lock, num));
        writeThread.start();

        try {
            writeThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("time = " + (System.currentTimeMillis() - currentTime));
    }
}
