package practice.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by zhangguijiang on 16/6/28.
 */
public class WorkTest {
    public static void main(String[] args){
        Worker worker = new RealWorker();
        InvocationHandler proxyHandler = new ProxyHandler(worker);

        Worker proxyWorker = (Worker) Proxy.newProxyInstance(worker.getClass().getClassLoader(),
                worker.getClass().getInterfaces(), proxyHandler);
        proxyWorker.work();
    }
}
