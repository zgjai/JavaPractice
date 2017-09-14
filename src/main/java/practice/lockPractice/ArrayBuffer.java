package practice.lockPractice;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangguijiang on 2017/7/31.
 */
public class ArrayBuffer<E> {

    private static final Logger logger = LoggerFactory.getLogger(ArrayBuffer.class);
    private final List<E> buffer;
    private final int cap;
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public ArrayBuffer(int size) {
        cap = size;
        this.buffer = Lists.newArrayListWithCapacity(size);
    }

    public void put(E item) {
        lock.lock();
        try {
            while (cap == buffer.size()) {
                notFull.await();
            }
            buffer.add(item);
            logger.info(Thread.currentThread().getName() + ": put " + item);
            notEmpty.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public E take() {
        lock.lock();
        try {
            while (0 == buffer.size()) {
                notEmpty.await();
            }
            E item = buffer.remove(buffer.size() - 1);
            logger.info(Thread.currentThread().getName() + ": take " + item);
            notFull.signal();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }
}
