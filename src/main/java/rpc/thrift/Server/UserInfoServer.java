package rpc.thrift.Server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import rpc.thrift.UserInfo.UserInfoService;
import rpc.thrift.UserInfo.UserInfoServiceImpl;

/**
 * @author zhangguijiang
 * @date 2018/4/25
 */
public class UserInfoServer {
    private static final int SERVER_PORT = 8090;
    private static final int SERVER_PORT1 = 8091;
    private static final int SERVER_PORT2 = 8092;
    private static final int SERVER_PORT3 = 8093;

    public static void main(String[] args) {
        UserInfoServer server = new UserInfoServer();
        server.startSimpleServer();
        // server.startTThreadPoolServer();
        // server.startTNonblockingServer();
        // server.startTHsHaServer();
    }

    private void startSimpleServer() {
        try {
            System.out.println("UserInfoServer start");
            TProcessor tProcessor = new UserInfoService.Processor<UserInfoService.Iface>(new UserInfoServiceImpl());
            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            TServer.Args args = new TServer.Args(serverTransport);
            args.processor(tProcessor);
            args.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TSimpleServer(args);
            server.serve();
        } catch (Exception e) {
            System.out.println("UserInfoServer error");
            e.printStackTrace();
        }
    }

    private void startTThreadPoolServer() {
        try {
            System.out.println("UserInfoServer start");
            TProcessor tProcessor = new UserInfoService.Processor<UserInfoService.Iface>(new UserInfoServiceImpl());
            TServerSocket serverTransport = new TServerSocket(SERVER_PORT1);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
            args.processor(tProcessor);
            args.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TThreadPoolServer(args);
            server.serve();
        } catch (Exception e) {
            System.out.println("UserInfoServer error");
            e.printStackTrace();
        }
    }

    private void startTNonblockingServer() {
        try {
            System.out.println("UserInfoServer start");
            TProcessor tProcessor = new UserInfoService.Processor<UserInfoService.Iface>(new UserInfoServiceImpl());
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(SERVER_PORT2);
            TNonblockingServer.Args args = new TNonblockingServer.Args(serverTransport);
            args.processor(tProcessor);
            args.transportFactory(new TFramedTransport.Factory());
            args.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TNonblockingServer(args);
            server.serve();
        } catch (Exception e) {
            System.out.println("UserInfoServer error");
            e.printStackTrace();
        }
    }

    private void startTHsHaServer() {
        try {
            System.out.println("UserInfoServer start");
            TProcessor tProcessor = new UserInfoService.Processor<UserInfoService.Iface>(new UserInfoServiceImpl());
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(SERVER_PORT3);
            THsHaServer.Args args = new THsHaServer.Args(serverTransport);
            args.processor(tProcessor);
            args.transportFactory(new TFramedTransport.Factory());
            args.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new THsHaServer(args);
            server.serve();
        } catch (Exception e) {
            System.out.println("UserInfoServer error");
            e.printStackTrace();
        }
    }
}
