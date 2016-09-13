package practice.ConcurrentPackageRead.AtomicPackage;

/**
 * Created by zhangguijiang on 16/9/8.
 */

import sun.misc.Unsafe;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

/**
 * An int value that may be updated atomically. See the java.util.concurrent.atomic package specification for
 * description of the properties of atomic variables. An AtomicInteger is used in applications such as atomically
 * incremented counters, and cannot be used as a replacement for an Integer. However, this class does extend Number to
 * allow uniform access by tools and utilities that deal with numerically-based classes.
 *
 * 一个可以被原子更新的整形变量.
 */
public class OwnAtomicInteger extends Number implements Serializable {
    private static final long serialVersionUID = 6214790243416807050L;

    private static final Unsafe unsafe;
    private volatile int value;
    private static final long valueOffset;

    static {
        try {
            final Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);

            valueOffset = unsafe.objectFieldOffset(OwnAtomicInteger.class.getDeclaredField("value"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public OwnAtomicInteger() {
    }

    public OwnAtomicInteger(int initialValue) {
        this.value = initialValue;
    }

    /**
     * Gets the current value.
     */
    public final int get() {
        return value;
    }

    /**
     * Sets to the given value.
     */
    public final void set(int newValue) {
        value = newValue;
    }

    /**
     * Eventually sets to the given value.
     */
    public final void lazySet(int newValue) {
        unsafe.putOrderedInt(this, valueOffset, newValue);
    }

    /**
     * Atomically sets the value to the given updated value if the current value == the expected value. 原子更新
     */
    public final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }

    /**
     * Atomically sets the value to the given updated value if the current value == the expected value. May fail
     * spuriously and does not provide ordering guarantees, so is only rarely an appropriate alternative to
     * compareAndSet.
     */
    public final boolean weakCompareAndSet(int expect, int update){
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }

    /**
     * Atomically updates the current value with the results of applying the given function to the current and given
     * values, returning the updated value. The function should be side-effect-free, since it may be re-applied when
     * attempted updates fail due to contention among threads. The function is applied with the current value as its
     * first argument, and the given update as the second argument.
     *
     */
    public final int accumulateAndGet(int x, IntBinaryOperator accumulatorFunction) {
        int prev, next;
        do {
            prev = get();
            next = accumulatorFunction.applyAsInt(prev, x);
        } while (!compareAndSet(prev, next));
        return next;
    }

    /**
     * Atomically updates the current value with the results of applying the given function to the current and given
     * values, returning the previous value. The function should be side-effect-free, since it may be re-applied when
     * attempted updates fail due to contention among threads. The function is applied with the current value as its
     * first argument, and the given update as the second argument.
     */
    public final int getAndAccumulate(int x, IntBinaryOperator accumulatorFunction) {
        int prev, next;
        do {
            prev = get();
            next = accumulatorFunction.applyAsInt(prev, x);
        } while (!compareAndSet(prev, next));
        return prev;
    }

    /**
     * Atomically adds the given value to the current value.
     */
    public final int addAndGet(int delta) {
        return unsafe.getAndAddInt(this, valueOffset, delta) + delta;
    }

    /**
     * Atomically adds the given value to the current value.
     */
    public final int getAndAdd(int delta) {
        return unsafe.getAndAddInt(this, valueOffset, delta);
    }

    /**
     * Atomically decrements by one the current value.
     */
    public final int decrementAndGet() {
        return unsafe.getAndAddInt(this, valueOffset, -1) - 1;
    }

    /**
     * Atomically decrements by one the current value.
     */
    public final int getAndDecrement() {
        return unsafe.getAndAddInt(this, valueOffset, -1);
    }

    /**
     * Atomically increments by one the current value.
     */
    public final int getAndIncrement() {
        return unsafe.getAndAddInt(this, valueOffset, 1);
    }

    /**
     * Atomically increments by one the current value.
     */
    public final int incrementAndGet() {
        return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
    }

    /**
     * Atomically sets to the given value and returns the old value.
     */
    public final int getAndSet(int newValue) {
        return unsafe.getAndSetInt(this, valueOffset, newValue);
    }

    /**
     * Atomically updates the current value with the results of applying the given function, returning the previous
     * value. The function should be side-effect-free, since it may be re-applied when attempted updates fail due to
     * contention among threads.
     */
    public final int getAndUpdate(IntUnaryOperator updateFunction) {
        int prev, next;
        do {
            prev = get();
            next = updateFunction.applyAsInt(prev);
        } while (!compareAndSet(prev, next));
        return prev;
    }

    /**
     * Atomically updates the current value with the results of applying the given function, returning the updated
     * value. The function should be side-effect-free, since it may be re-applied when attempted updates fail due to
     * contention among threads.
     */
    public final int updateAndGet(IntUnaryOperator updateFunction) {
        int prev, next;
        do {
            prev = get();
            next = updateFunction.applyAsInt(prev);
        } while (!compareAndSet(prev, next));
        return next;
    }

    /**
     * Returns the value of this AtomicInteger as an int.
     */
    @Override
    public int intValue() {
        return get();
    }

    /**
     * Returns the value of this AtomicInteger as a long after a widening primitive conversion.
     */
    @Override
    public long longValue() {
        return (long) get();
    }

    /**
     * Returns the value of this AtomicInteger as a float after a widening primitive conversion.
     */
    @Override
    public float floatValue() {
        return (float) get();
    }

    /**
     * Returns the value of this AtomicInteger as a double after a widening primitive conversion.
     */
    @Override
    public double doubleValue() {
        return (double) get();
    }

    @Override
    public String toString() {
        return Integer.toString(get());
    }
}
