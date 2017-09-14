package practice.lockPractice;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by zhangguijiang on 2017/8/15.
 */
public class Mutex implements Lock {

    // 内部静态类Sync，继承AQS，Mutex的所有操作其实都是委托Sync实现的
    private final Sync sync = new Sync();

    private static final class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int ignore) {
            // 通过CAS的方式改变state的值，成功则表示加锁成功
            if (compareAndSetState(0, 1)) {
                // 设置独占锁的线程为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int ignore) {
            // 校验独占线程是否为当前线程
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            // 设置独占线程为null
            setExclusiveOwnerThread(null);
            // 直接设置state的值，不需要CAS的原因是，当前线程还占用着该锁，没有线程安全的问题
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
