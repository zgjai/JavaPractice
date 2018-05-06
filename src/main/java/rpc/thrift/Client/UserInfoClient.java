package rpc.thrift.Client;

import org.apache.thrift.TApplicationException;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import rpc.thrift.UserInfo.UserInfoService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangguijiang
 * @date 2018/4/25
 */
public class UserInfoClient {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8090;
    public static final int SERVER_PORT1 = 8091;
    public static final int SERVER_PORT2 = 8092;
    public static final int SERVER_PORT3 = 8093;
    public static final int TIMEOUT = 30000;

    private void startClient(int userId) {
        TTransport transport = null;
        try {
            transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
            TProtocol protocol = new TBinaryProtocol(transport);
            UserInfoService.Client client = new UserInfoService.Client(protocol);
            transport.open();
            String result = client.getUserNameById(userId);
            System.out.println("The result = " + result);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            if (e instanceof TApplicationException
                    && ((TApplicationException) e).getType() == TApplicationException.MISSING_RESULT) {
                System.out.println("The result is null");
            }
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

    private void startClientAsync(int userId, int port) {
        TNonblockingTransport transport = null;
        try {
            TAsyncClientManager clientManager = new TAsyncClientManager();
            transport = new TNonblockingSocket(SERVER_IP, port, TIMEOUT);
            TProtocolFactory tProtocol = new TCompactProtocol.Factory();
            UserInfoService.AsyncClient asyncClient = new UserInfoService.AsyncClient(tProtocol, clientManager, transport);

            CountDownLatch latch = new CountDownLatch(1);
            AsyncCallback callBack = new AsyncCallback(latch);
            System.out.println("call method sayHello start ...");
            asyncClient.getUserNameById(userId, callBack);
            System.out.println("call method sayHello .... end");
            boolean wait = latch.await(30, TimeUnit.SECONDS);

            System.out.println("latch.await =:" + wait);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("startClientAsync end");
    }

    private static class AsyncCallback implements AsyncMethodCallback<String> {
        private CountDownLatch latch;

        AsyncCallback(CountDownLatch latch) {
            this.latch = latch;
        }


        @Override
        public void onComplete(String response) {
            System.out.println("onComplete");
            try {
                Thread.sleep(1000L);
                System.out.println("AsyncCall result =:" + response);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }

        @Override
        public void onError(Exception exception) {
            System.out.println("onError :" + exception.getMessage());
            latch.countDown();
        }
    }
}
