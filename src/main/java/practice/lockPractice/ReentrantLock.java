package practice.lockPractice;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by zhangguijiang on 2017/8/4.
 */
public class ReentrantLock implements Lock {

    private final Sync sync;

    // 抽象同步类
    abstract static class Sync extends AbstractQueuedSynchronizer {
        // 由公平与非公平的同步类各自实现加锁的方法
        abstract void lock();

        // 非公平的tryLock
        // 这个方法在公平锁跟非公平锁的类中都需要用到
        final boolean nonfairTryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            // 获取当前state
            int c = getState();
            if (c == 0) {
                // c == 0 表示当前没有线程占用
                // cas尝试获取锁
                if (compareAndSetState(0, acquires)) {
                    // 获取成功则设置当前线程为占用线程
                    setExclusiveOwnerThread(current);
                    return true;
                }
            } else if (current == getExclusiveOwnerThread()) {
                // 当前线程已经占用了该锁
                int nextc = c + acquires;
                if (nextc < 0) {
                    throw new Error("Maximum lock count exceeded");
                }
                setState(nextc);
                return true;
            }
            return false;
        }

        @Override
        protected final boolean tryRelease(int releases) {
            // 获取返还后的state值
            int c = getState() - releases;
            // 如果当前线程不是独占线程，抛出异常
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            // free为true时表示锁被完全释放
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }

        @Override
        protected boolean isHeldExclusively() {
            return Thread.currentThread() == getExclusiveOwnerThread();
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }

        final Thread getOwner() {
            return getState() == 0 ? null : getExclusiveOwnerThread();
        }

        final int getHoldCount() {
            return isHeldExclusively() ? getState() : 0;
        }

        final boolean isLocked() {
            return getState() != 0;
        }
    }

    // 非公平的同步类
    static final class NonFairSync extends Sync {
        @Override
        void lock() {
            // 直接尝试抢占资源
            if (compareAndSetState(0, 1)) {
                // 抢占成功则将独占线程设置为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
            } else {
                // 抢占失败则进入正常流程
                acquire(1);
            }
        }

        @Override
        protected boolean tryAcquire(int acquires) {
            return nonfairTryAcquire(acquires);
        }
    }

    // 公平的同步类
    static final class FairSync extends Sync {
        @Override
        void lock() {
            // 公平抢占，直接进入正常流程
            acquire(1);
        }

        @Override
        protected boolean tryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {
                // 当前没有线程占用资源，判断等待队列中有没有等待节点，没有则试图抢占资源
                if (!hasQueuedPredecessors() && compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            } else if (getExclusiveOwnerThread() == current) {
                // 重入
                int nextc = c + acquires;
                if (nextc < 0)
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
            }
            return false;
        }
    }

    public ReentrantLock() {
        // 默认非公平锁，性能更佳
        sync = new NonFairSync();
    }

    public ReentrantLock(boolean fair) {
        sync = fair ? new FairSync() : new NonFairSync();
    }

    @Override
    public void lock() {
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.nonfairTryAcquire(1);
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

    public int getHoldCount() {
        return sync.getHoldCount();
    }

    public boolean isHeldByCurrentThread() {
        return sync.isHeldExclusively();
    }

    public boolean isLocked() {
        return sync.isLocked();
    }

    public final boolean isFair() {
        return sync instanceof FairSync;
    }

    protected Thread getOwner() {
        return sync.getOwner();
    }

    public final boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    public final boolean hasQueuedThread(Thread thread) {
        return sync.isQueued(thread);
    }

    public final int getQueueLength() {
        return sync.getQueueLength();
    }

    protected Collection<Thread> getQueuedThreads() {
        return sync.getQueuedThreads();
    }

    public boolean hasWaiters(Condition condition) {
        if (condition == null)
            throw new NullPointerException();
        if (!(condition instanceof AbstractQueuedSynchronizer.ConditionObject))
            throw new IllegalArgumentException("not owner");
        return sync.hasWaiters((AbstractQueuedSynchronizer.ConditionObject) condition);
    }

    public int getWaitQueueLength(Condition condition) {
        if (condition == null)
            throw new NullPointerException();
        if (!(condition instanceof AbstractQueuedSynchronizer.ConditionObject))
            throw new IllegalArgumentException("not owner");
        return sync.getWaitQueueLength((AbstractQueuedSynchronizer.ConditionObject) condition);
    }

    protected Collection<Thread> getWaitingThreads(Condition condition) {
        if (condition == null)
            throw new NullPointerException();
        if (!(condition instanceof AbstractQueuedSynchronizer.ConditionObject))
            throw new IllegalArgumentException("not owner");
        return sync.getWaitingThreads((AbstractQueuedSynchronizer.ConditionObject) condition);
    }

    public String toString() {
        Thread o = sync.getOwner();
        return super.toString() + ((o == null) ?
                "[Unlocked]" :
                "[Locked by thread " + o.getName() + "]");
    }
}
