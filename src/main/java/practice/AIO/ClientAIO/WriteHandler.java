package practice.AIO.ClientAIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangguijiang on 16/7/4.
 */
public class WriteHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel clientChannel;
    private CountDownLatch latch;

    public WriteHandler(AsynchronousSocketChannel clientChannel, CountDownLatch latch) {
        this.clientChannel = clientChannel;
        this.latch = latch;
    }

    public void completed(Integer result, ByteBuffer buffer) {
        // 完成全部数据的写入
        if (buffer.hasRemaining()) {
            clientChannel.write(buffer, buffer, this);
        } else {
            // 读取数据
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            clientChannel.read(readBuffer, readBuffer, new ReadHandler(clientChannel, latch));
        }
    }

    public void failed(Throwable exc, ByteBuffer attachment) {
        System.err.println("数据发送失败...");
        try {
            clientChannel.close();
            latch.countDown();
        } catch (IOException e) {
        }
    }
}