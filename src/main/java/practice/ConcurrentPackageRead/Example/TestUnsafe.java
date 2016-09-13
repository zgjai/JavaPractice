package practice.ConcurrentPackageRead.Example;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangguijiang on 16/8/3.
 */
public class TestUnsafe {


    private long test1;
    private int test2;
    private int test3;
    private static int byteArrayBaseOffset;
    private static volatile int temp;
    private int test4;
    private final String test5 = "a";

    public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe UNSAFE = (Unsafe) theUnsafe.get(null);
        System.out.println(UNSAFE);

        byte[] data = new byte[10];
        System.out.println(Arrays.toString(data));
        byteArrayBaseOffset = UNSAFE.arrayBaseOffset(byte[].class);
        temp = UNSAFE.arrayIndexScale(byte[].class);

        System.out.println(byteArrayBaseOffset);
        System.out.println(temp);
        UNSAFE.putByte(data, byteArrayBaseOffset, (byte) 1);
        UNSAFE.putByte(data, byteArrayBaseOffset + 5, (byte) 5);
        System.out.println(Arrays.toString(data));

        //int[] intArray = new int[10];
        int base = UNSAFE.arrayBaseOffset(int[].class);
        int scale = UNSAFE.arrayIndexScale(int[].class);
        int shift = 31 - Integer.numberOfLeadingZeros(scale);
        int i = 2;
        long add = ((long) i << shift) + base;
        System.out.println(base);
        System.out.println(scale);
        System.out.println(shift);
        System.out.println(add);


        long offset = UNSAFE.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
        long offset1 = UNSAFE.objectFieldOffset(TestUnsafe.class.getDeclaredField("test1"));
        long offset2 = UNSAFE.objectFieldOffset(TestUnsafe.class.getDeclaredField("test2"));
        long offset3 = UNSAFE.objectFieldOffset(TestUnsafe.class.getDeclaredField("test3"));
        long offset4 = UNSAFE.objectFieldOffset(TestUnsafe.class.getDeclaredField("test4"));
        long offset5 = UNSAFE.objectFieldOffset(TestUnsafe.class.getDeclaredField("test5"));
        System.out.println(offset);
        System.out.println(offset1);
        System.out.println(offset2);
        System.out.println(offset3);
        System.out.println(offset4);
        System.out.println(offset5);

    }

}
