package practice.JTool;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangguijiang on 16/7/13.
 */
public class DeadLockTest {
    public static void main(String[] args){
        /*String lock1 = "lockOne";
        String lock2 = "lockTwo";

        Thread thread1 = new Thread(new SynchronizedDeadLock("Thread1", lock1, lock2));
        Thread thread2 = new Thread(new SynchronizedDeadLock("Thread2", lock2, lock1));

        thread1.start();
        thread2.start();*/

        ReentrantLock lockOne = new ReentrantLock();
        ReentrantLock lockTwo = new ReentrantLock();

        Thread threadOne = new Thread(new ReentrantLockDeadLock("Thread1", lockOne, lockTwo));
        Thread threadTwo = new Thread(new ReentrantLockDeadLock("Thread2", lockTwo, lockOne));

        threadOne.start();
        threadTwo.start();
    }
}
