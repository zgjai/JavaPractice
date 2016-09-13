package practice.ProxyPattern;

/**
 * Created by zhangguijiang on 16/6/7.
 */
public class WorkerProxy implements Worker{

    private Worker concreteWorker;
    private long workTime;

    public WorkerProxy(Worker concreteWorker) {
        this.concreteWorker = concreteWorker;
    }

    public void work() {
        long beginTime = System.currentTimeMillis();
        concreteWorker.work();
        this.workTime = System.currentTimeMillis() - beginTime;
    }

    public long getWorkTime(){
        return this.workTime;
    }
}
