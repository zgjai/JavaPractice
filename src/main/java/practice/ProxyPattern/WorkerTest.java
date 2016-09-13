package practice.ProxyPattern;

/**
 * Created by zhangguijiang on 16/6/7.
 */
public class WorkerTest {
    public static void main(String[] args){
        Worker worker = new ConcreteWorker("甲");
        WorkerProxy workerProxy = new WorkerProxy(worker);
        workerProxy.work();
        System.out.println("work time: " + workerProxy.getWorkTime());
    }
}
