package practice.Compute;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangguijiang on 16/6/26.
 */
public class AreaCalculateTest {
    public static void main(String[] args){
        AreaCalculateTest test = new AreaCalculateTest();
        test.test();
    }

    private void test(){
        try {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 0,
                    TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
            List<Future<Float>> resultList = new LinkedList<Future<Float>>();

            float start = 1;
            float end = 10;
            for (int i=0; i<10; i++){
                AreaCalculateTask task = new AreaCalculateTask(start, end);
                Future<Float> result = threadPoolExecutor.submit(task);
                resultList.add(result);
                start = end;
                end += 10;
            }

            threadPoolExecutor.shutdown();

            float areaSum = 0;
            for (int i=0; i<resultList.size(); i++){
                areaSum = areaSum + resultList.get(i).get();
            }
            System.out.println("面积约为:" + areaSum);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
