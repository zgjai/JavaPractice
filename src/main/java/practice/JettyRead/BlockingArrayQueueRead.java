package practice.JettyRead;

import org.eclipse.jetty.io.ArrayByteBufferPool;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

/**
 * Created by zhangguijiang on 16/7/22.
 */
public class BlockingArrayQueueRead {
    QueuedThreadPool queuedThreadPool;
    ArrayByteBufferPool arrayByteBufferPool;

    /**
     * A BlockingQueue backed by a circular array capable or growing.
     *
     * <p/>
     * This queue is uses a variant of the two lock queue algorithm to provide an efficient queue or list backed by a growable circular array.
     * <p/>
     * Unlike {@link java.util.concurrent.ArrayBlockingQueue}, this class is able to grow and provides a blocking put call.
     * <p/>
     * The queue has both a capacity (the size of the array currently allocated) and a max capacity (the maximum size that may be allocated), which defaults to
     * {@link Integer#MAX_VALUE}.
     *
     * @param <E>
     *            The element type
     */
}
