package practice.ServerAndClient;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangguijiang on 16/7/19.
 */
public class Server {
    private Integer listenPort;

    public Server(Integer listenPort) {
        this.listenPort = listenPort;
    }

    public void startListen() {
        try {
            ServerSocket serverSocket = new ServerSocket(listenPort);
            boolean flag = false;
            while (!flag){
                Socket clientSocket = serverSocket.accept();
                Thread thread = new Thread(new ServerThread(clientSocket));
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Integer port = 9000;
        Server server = new Server(port);

        server.startListen();
    }
}
