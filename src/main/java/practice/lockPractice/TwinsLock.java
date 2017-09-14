package practice.lockPractice;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by zhangguijiang on 2017/8/3.
 */
public class TwinsLock implements Lock {

    // 内部静态类Sync，作用同Mutex中的Sync
    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer {

        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large than zero.");
            }
            // 将state设置为可共享获取的次数
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            // 计算是否可获取锁
            int current = getState();
            int newCount = current - reduceCount;
            if (newCount < 0) {
                return newCount;
            }
            // CAS设置state
            if (compareAndSetState(current, newCount)) {
                return newCount;
            }
            return -1;
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            int current = getState();
            int newCount = current + returnCount;
            // 这里需要通过CAS的方式设置state，因为多线程共享锁，存在线程安全问题
            return compareAndSetState(current, newCount);
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        // Condition不支持共享模式下使用
        throw new UnsupportedOperationException();
    }
}
