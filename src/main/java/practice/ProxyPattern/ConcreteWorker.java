package practice.ProxyPattern;

/**
 * Created by zhangguijiang on 16/6/7.
 */
public class ConcreteWorker implements Worker {

    private String workerName;

    public ConcreteWorker(String workerName) {
        this.workerName = workerName;
    }

    public void work() {
        try {
            System.out.println(this.workerName + " working...");
            Thread.sleep(1000);
            System.out.println(this.workerName + " stopped");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
