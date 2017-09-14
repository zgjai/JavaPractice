package practice.lockPractice;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by zhangguijiang on 2017/8/7.
 */
public class ReentrantReadWriteLock implements ReadWriteLock {

    private final ReentrantReadWriteLock.ReadLock readLock;
    private final ReentrantReadWriteLock.WriteLock writeLock;

    private final Sync sync;

    public ReentrantReadWriteLock() {
        this(false);
    }

    public ReentrantReadWriteLock(boolean fair) {
        sync = fair ? new FairSync() : new NonFairSync();
        readLock = new ReadLock(this);
        writeLock = new WriteLock(this);
    }

    public ReentrantReadWriteLock.ReadLock readLock() {
        return readLock;
    }

    public ReentrantReadWriteLock.WriteLock writeLock() {
        return writeLock;
    }

    abstract static class Sync extends AbstractQueuedSynchronizer {

        // AQS中的state变量是一个32位的int值，读写锁将该值分成两个16位的值使用
        // 高16位用于读锁，低16位用于写锁
        static final int SHARED_SHIFT = 16;
        static final int SHARED_UNIT = (1 << SHARED_SHIFT);
        static final int MAX_COUNT = (1 << SHARED_SHIFT) - 1;
        static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

        // 获取共享模式的state值
        static int sharedCount(int c) {
            return c >>> SHARED_SHIFT;
        }

        // 获取独占模式的state值
        static int exclusiveCount(int c) {
            return c & EXCLUSIVE_MASK;
        }

        // 保存每个线程占用的读资源的数量
        static final class HoldCounter {
            int count = 0;
            // Use id, not reference, to avoid garbage retention
            final long tid = getThreadId(Thread.currentThread());
        }

        static final class ThreadLocalHoldCounter extends ThreadLocal<HoldCounter> {
            public HoldCounter initialValue() {
                return new HoldCounter();
            }
        }

        // ThreadLocal保存每个线程的HoldCounter变量
        private transient ThreadLocalHoldCounter readHolds;

        // 存储最近一次获取读锁的线程的HoldCounter变量
        private transient HoldCounter cachedHoldCounter;

        private transient Thread firstReader = null;
        private transient int firstReaderHoldCount;

        Sync() {
            readHolds = new ThreadLocalHoldCounter();
            // 通过读写一个volatile变量来确保readHolds变量的可见性
            setState(getState());
        }

        abstract boolean readerShouldBlock();

        abstract boolean writerShouldBlock();

        @Override
        protected boolean tryRelease(int releases) {
            if (!isHeldExclusively()) {
                throw new IllegalMonitorStateException();
            }
            int nextc = getState() - releases;
            boolean free = exclusiveCount(nextc) == 0;
            if (free) {
                setExclusiveOwnerThread(null);
            }
            setState(nextc);
            return free;
        }

        @Override
        protected boolean tryAcquire(int acquires) {
            // 1.如果read count不为0，或者write count不为0且owner不是当前线程，失败
            // 2.如果count已经饱和，失败
            // 3.如果是重入的情况或者等待队列的规则允许获取锁，则可以获取锁
            Thread current = Thread.currentThread();
            int c = getState();
            int w = exclusiveCount(c);
            if (c != 0) {
                // 情形1
                if (w == 0 || getExclusiveOwnerThread() != current) {
                    return false;
                }
                // 情形2
                if (w + exclusiveCount(acquires) > MAX_COUNT) {
                    throw new Error("Maximum lock count exceeded");
                }
                setState(c + acquires);
                return true;
            }
            // 没有线程占用锁
            if (writerShouldBlock() || !compareAndSetState(c, c + acquires)) {
                return false;
            }
            setExclusiveOwnerThread(current);
            return true;
        }

        protected final boolean tryReleaseShared(int unused) {
            Thread current = Thread.currentThread();
            if (firstReader == current) {
                if (firstReaderHoldCount == 1) {
                    // 如果当前线程完全释放了资源，还原firstReader
                    firstReader = null;
                } else {
                    firstReaderHoldCount--;
                }
            } else {
                // 缓存的最后一次获取共享锁的线程获取的资源数
                HoldCounter rh = cachedHoldCounter;
                if (rh == null || rh.tid != getThreadId(current)) {
                    // 如果缓存的不是当前线程的则从ThreadLocal中获取
                    rh = readHolds.get();
                }
                int count = rh.count;
                if (count <= 1) {
                    // 释放完全后，从ThreadLocal中删除，以便gc
                    readHolds.remove();
                    if (count <= 0) {
                        throw unmatchedUnlockException();
                    }
                }
                --rh.count;
            }
            for (;;) {
                int c = getState();
                int nextc = c - SHARED_UNIT;
                if (compareAndSetState(c, nextc)) {
                    return nextc == 0;
                }
            }
        }

        private IllegalMonitorStateException unmatchedUnlockException() {
            return new IllegalMonitorStateException("attempt to unlock read lock, not locked by current thread");
        }

        protected final int tryAcquireShared(int unused) {
            Thread current = Thread.currentThread();
            int c = getState();
            if (exclusiveCount(c) != 0 && getExclusiveOwnerThread() != current) {
                // 写锁被占用且不为当前线程，获取锁失败
                return -1;
            }
            int r = sharedCount(c);
            if (!readerShouldBlock() && r < MAX_COUNT && compareAndSetState(c, c + SHARED_UNIT)) {
                if (r == 0) {
                    firstReader = current;
                    firstReaderHoldCount = 1;
                } else if (firstReader == current) {
                    firstReaderHoldCount++;
                } else {
                    HoldCounter rh = cachedHoldCounter;
                    if (rh == null || rh.tid != getThreadId(current)) {
                        cachedHoldCounter = rh = readHolds.get();
                    } else if (rh.count == 0) {
                        readHolds.set(rh);
                    }
                    rh.count++;
                }
                return 1;
            }
            return fullTryAcquireShared(current);
        }

        // fullTryAcquireShared 这个方法其实是 tryAcquireShared 的冗余(redundant)方法, 主要补足 readerShouldBlock 导致的获取等待 和 CAS 修改 AQS
        // 中state 值失败进行的修补工作
        final int fullTryAcquireShared(Thread current) {
            HoldCounter rh = null;
            for (;;) {
                int c = getState();
                if (exclusiveCount(c) != 0) {
                    if (getExclusiveOwnerThread() != current) {
                        // 有其他线程获取了写锁，失败，加入同步队列
                        return -1;
                    }
                } else if (readerShouldBlock()) {
                    if (firstReader == current) {
                        // 重入获取读锁
                    } else {
                        if (rh == null) {
                            rh = cachedHoldCounter;
                            if (rh == null || rh.tid != getThreadId(current)) {
                                rh = readHolds.get();
                                if (rh.count == 0) {
                                    readHolds.remove();
                                }
                            }
                        }
                        if (rh.count == 0) {
                            return -1;
                        }
                    }
                }
                if (sharedCount(c) == MAX_COUNT)
                    throw new Error("Maximum lock count exceeded");
                // cas加锁
                if (compareAndSetState(c, c + SHARED_UNIT)) {
                    if (sharedCount(c) == 0) {
                        // 当前没有线程占用读锁
                        firstReader = current;
                        firstReaderHoldCount = 1;
                    } else if (firstReader == current) {
                        firstReaderHoldCount++;
                    } else {
                        if (rh == null) {
                            rh = cachedHoldCounter;
                        }
                        if (rh == null || rh.tid != getThreadId(current)) {
                            rh = readHolds.get();
                        } else if (rh.count == 0) {
                            readHolds.set(rh);
                        }
                        rh.count++;
                        cachedHoldCounter = rh;
                    }
                    return 1;
                }
            }
        }

        // 获取写锁
        final boolean tryWriteLock() {
            Thread current = Thread.currentThread();
            int c = getState();
            if (c != 0) {
                int w = exclusiveCount(c);
                if (w == 0 || getExclusiveOwnerThread() != current) {
                    // 有线程占用读锁或者有非当前线程占用写锁，失败
                    return false;
                }
                if (w == MAX_COUNT) {
                    throw new Error("Maximum lock count exceeded");
                }
            }
            if (!compareAndSetState(c, c + 1)) {
                return false;
            }
            setExclusiveOwnerThread(current);
            return true;
        }

        final boolean tryReadLock() {
            Thread current = Thread.currentThread();
            for (;;) {
                int c = getState();
                if (exclusiveCount(c) != 0 && getExclusiveOwnerThread() != current) {
                    // 写锁被占用，且不为当前线程，则直接失败
                    // 如果写锁被占用，且是当前线程的话，是可以继续获取读锁的，称为锁降级
                    return false;
                }
                int r = sharedCount(c);
                if (r == MAX_COUNT) {
                    throw new Error("Maximum lock count exceeded");
                }
                if (compareAndSetState(c, c + SHARED_UNIT)) {
                    if (r == 0) {
                        firstReader = current;
                        firstReaderHoldCount = 1;
                    } else if (firstReader == current) {
                        firstReaderHoldCount++;
                    } else {
                        HoldCounter rh = cachedHoldCounter;
                        if (rh == null || rh.tid != getThreadId(current)) {
                            cachedHoldCounter = rh = readHolds.get();
                        } else if (rh.count == 0) {
                            readHolds.set(rh);
                        }
                        rh.count++;
                    }
                    return true;
                }
            }
        }

        protected final boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }

        final Thread getOwner() {
            return ((exclusiveCount(getState()) == 0) ? null : getExclusiveOwnerThread());
        }

        final int getReadLockCount() {
            return sharedCount(getState());
        }

        final boolean isWriteLocked() {
            return exclusiveCount(getState()) != 0;
        }

        final int getWriteHoldCount() {
            return isHeldExclusively() ? exclusiveCount(getState()) : 0;
        }

        final int getReadHoldCount() {
            if (getReadLockCount() == 0) {
                return 0;
            }
            Thread current = Thread.currentThread();
            if (firstReader == current) {
                return firstReaderHoldCount;
            }
            HoldCounter rh = cachedHoldCounter;
            if (rh != null && rh.tid == getThreadId(current)) {
                return rh.count;
            }
            int count = readHolds.get().count;
            if (count == 0) {
                readHolds.remove();
            }
            return count;
        }
    }

    static final class NonFairSync extends Sync {
        @Override
        boolean readerShouldBlock() {
            //return apparentlyFirstQueuedIsExclusive();
            return true;
        }

        @Override
        boolean writerShouldBlock() {
            return false;
        }
    }

    static final class FairSync extends Sync {
        @Override
        boolean readerShouldBlock() {
            return hasQueuedPredecessors();
        }

        @Override
        boolean writerShouldBlock() {
            return hasQueuedPredecessors();
        }
    }

    public static class ReadLock implements Lock {
        private final Sync sync;

        public ReadLock(ReentrantReadWriteLock lock) {
            sync = lock.sync;
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
            return sync.tryReadLock();
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
            throw new UnsupportedOperationException();
        }

        public String toString() {
            int r = sync.getReadLockCount();
            return super.toString() + "[Read locks = " + r + "]";
        }
    }

    public static class WriteLock implements Lock {
        private Sync sync;

        protected WriteLock(ReentrantReadWriteLock lock) {
            sync = lock.sync;
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
            return sync.tryWriteLock();
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

        public String toString() {
            Thread o = sync.getOwner();
            return super.toString() + ((o == null) ? "[Unlocked]" : "[Locked by thread " + o.getName() + "]");
        }

        public boolean isHeldByCurrentThread() {
            return sync.isHeldExclusively();
        }

        public int getHoldCount() {
            return sync.getWriteHoldCount();
        }
    }

    static final long getThreadId(Thread thread) {
        return UNSAFE.getLongVolatile(thread, TID_OFFSET);
    }

    // Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;
    private static final long TID_OFFSET;
    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> tk = Thread.class;
            TID_OFFSET = UNSAFE.objectFieldOffset(tk.getDeclaredField("tid"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
