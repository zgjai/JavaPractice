package practice.Proxy;

/**
 * Created by zhangguijiang on 16/6/28.
 */
public class RealWorker implements Worker {

    public RealWorker() {
    }

    public void work() {
        try {
            System.out.println("work start");
            Thread.sleep(3000);
            System.out.println("work end");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
