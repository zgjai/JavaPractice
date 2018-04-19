package RpcFramework;

import java.net.InetSocketAddress;

import RpcFramework.Consumer.RpcImporter;
import RpcFramework.Producer.EchoService;
import RpcFramework.Producer.EchoServiceImpl;
import RpcFramework.Producer.RpcExporter;

/**
 * Created by zhangguijiang on 2018/3/28.
 */
public class Main {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                RpcExporter.exporter("localhost", 8088);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        RpcImporter<EchoService> importer = new RpcImporter<>();
        EchoService echo = importer.importer(EchoServiceImpl.class, new InetSocketAddress("localhost", 8088));
        System.out.println(echo.echo("Are you ok ?"));
    }
}
