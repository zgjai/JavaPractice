package practice.atomicPractice;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by zhangguijiang on 2017/7/25.
 */
public class AtomicIntegerArrayTest {

    private static int[] array = new int[] { 1, 3, 4, -10, 2 };
    private static AtomicIntegerArray atomicArray = new AtomicIntegerArray(array);

    public static void main(String[] args) {
        Unsafe unsafe = getUnsafe();
        if (unsafe == null) {
            return;
        }
        int scale = unsafe.arrayIndexScale(int[].class);
        System.out.println("scale: " + scale);
        int shift = 31 - Integer.numberOfLeadingZeros(scale);
        System.out.println("shift: " + shift);
        System.out.println(Integer.numberOfLeadingZeros(31));
    }

    private static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
