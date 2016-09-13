package practice;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangguijiang on 16/6/18.
 */
public class PrintPoolException {

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(11, 100, 1, TimeUnit.MINUTES,
            new ArrayBlockingQueue<Runnable>(10000)) {

        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            printException(r, t);
        }
    };

    private static void printException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone())
                    future.get();
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null)
            System.out.println(t.getMessage() + t);
    }
}
