package practice.ServerAndClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zhangguijiang on 16/7/19.
 */
public class ServerThread implements Runnable {

    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String result = bufferedReader.readLine();
            System.out.println("Client say : " + result);

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.print(requestUrl(result));
            printWriter.flush();

            printWriter.close();
            bufferedReader.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String requestUrl(String url){
        String result = "";
        try {
            URL urlForRequest = new URL(url);
            URLConnection urlConnection = urlForRequest.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String lineResult;
            while ((lineResult = bufferedReader.readLine()) != null){
                result = result + " " + lineResult;
            }
            ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
