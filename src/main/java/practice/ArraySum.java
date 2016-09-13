package practice;

/**
 * Created by zhangguijiang on 16/6/20.
 */
public class ArraySum {
    public static void main(String[] args) {
        int oddSum = 0;
        int evenSum = 0;
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        try {
            for (int i = 0; i < array.length; i++) {
                if (i % 2 == 0) {
                    evenSum += array[i];
                } else {
                    oddSum += array[i];
                }
            }
        } catch (ArrayIndexOutOfBoundsException e1) {
            e1.printStackTrace();
        } catch (ArithmeticException e2) {
            e2.printStackTrace();
        } finally {
            System.out.println("奇数和为: " + oddSum);
            System.out.println("偶数和为: " + evenSum);
        }
    }
}
