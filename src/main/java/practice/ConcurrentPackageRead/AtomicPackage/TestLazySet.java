package practice.ConcurrentPackageRead.AtomicPackage;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by zhangguijiang on 16/9/20.
 */
public class TestLazySet {

    private static Unsafe unsafe;
    private static final long valueOffset;
    private volatile int value;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            valueOffset = unsafe.objectFieldOffset(OwnAtomicInteger.class.getDeclaredField("value"));
        } catch (Exception e) {
            throw new Error("he");
        }
    }

    public void test(){
        unsafe.putIntVolatile(this, valueOffset, 1);
        unsafe.putOrderedInt(this, valueOffset, 2);
    }

    public static void main(String[] args){
        TestLazySet test = new TestLazySet();
        test.test();
    }
}
