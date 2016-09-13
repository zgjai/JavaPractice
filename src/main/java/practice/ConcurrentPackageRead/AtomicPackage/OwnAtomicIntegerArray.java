package practice.ConcurrentPackageRead.AtomicPackage;

/**
 * Created by zhangguijiang on 16/9/12.
 */

import sun.misc.Unsafe;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * An int array in which elements may be updated atomically. See the java.util.concurrent.atomic package specification
 * for description of the properties of atomic variables.
 */
public class OwnAtomicIntegerArray implements Serializable{

    private static final Unsafe unsafe;
    //数组头的偏移值
    private static final long arrayBaseOffset;
    //用于计算数组中元素的偏移值
    private static final int shift;
    //数组
    private final int[] array;

    static {
        try {
            final Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            //加快反射运行速度,跳过安全检查
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe)theUnsafe.get(null);

            arrayBaseOffset = unsafe.arrayBaseOffset(int[].class);

            shift = 0;
        } catch (Exception e) {
            throw new Error(e);
        }
    }


    public OwnAtomicIntegerArray(int[] array) {
        this.array = array;
    }

    public OwnAtomicIntegerArray(int length) {
        this.array = new int[length];
    }
}
