package practice;

import java.util.concurrent.RecursiveTask;

/**
 * Created by zhangguijiang on 16/6/21.
 */
public class AreaCalculate {
    public static void main(String[] args){
        System.out.println("计算开始");
        long start = System.currentTimeMillis();
        float areaSum = 0;
        float i = 100;
        for (int k=1; k<9900; k++){
            float height = 100/i;
            areaSum += height * 0.01;
            i++;
        }
        System.out.println(i);
        long time = System.currentTimeMillis() - start;
        System.out.println("面积约为:4.610034" + areaSum + "\n" + "time:" + time);
    }
}
