package practice.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhangguijiang on 16/6/28.
 */
public class ProxyHandler implements InvocationHandler {

    private Object obj;

    public ProxyHandler(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = method.invoke(obj, args);
        long workTime = System.currentTimeMillis() - beginTime;
        System.out.println("workTime: " + workTime);
        return result;
    }
}
