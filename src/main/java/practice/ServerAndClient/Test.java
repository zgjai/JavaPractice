package practice.ServerAndClient;

import java.io.InputStream;

/**
 * Created by zhangguijiang on 16/7/19.
 */
public class Test {
    public static void main(String[] args){
        String ip = "127.0.0.1";
        Integer port = 9000;

        Client client = new Client(ip, port);

        String url = "http://www.baidu.com";
        client.request(url);
    }
}
