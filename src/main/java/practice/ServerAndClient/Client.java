package practice.ServerAndClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhangguijiang on 16/7/19.
 */
public class Client {

    private String ip;
    private Integer port;

    public Client(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public String request(String url){
        try {
            Socket socket = new Socket(ip, port);
            socket.setSoTimeout(60000);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(url);
            printWriter.flush();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String result = bufferedReader.readLine();
            System.out.println("Server say : " + result);

            printWriter.close();
            bufferedReader.close();
            socket.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
