package practice.Compute;

import java.util.concurrent.Callable;

/**
 * Created by zhangguijiang on 16/6/26.
 */
public class AreaCalculateTask implements Callable<Float>{
    private float start;
    private float end;

    public AreaCalculateTask(float start, float end) {
        this.start = start;
        this.end = end;
    }

    public Float call() throws Exception {
        float areaSum = 0;
        for (float i=start*100; i<end*100;i++){
            float height = 100/i;
            areaSum += height * 0.01;
        }
        return areaSum;
    }
}
