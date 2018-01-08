package SimpleHttpServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * Created by zhangguijiang on 2017/11/23.
 */
public class HttpServer {
    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    private int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String line = null;
        boolean isFirstLine = true;
        Request request = new Request();
        List<String> headerLines = Lists.newArrayList();
        while ((line = input.readLine()) != null) {
            if (isFirstLine) {
                request.initRequestProps(line);
                isFirstLine = false;
            }
            if (line.length() == 0) {

                break;
            } else {
                headerLines.add(line);
            }
        }
        output.write("Hello Http Server!");
        output.flush();
        input.close();
        output.close();
    }

    public static void main(String[] args) {
        try {
            HttpServer httpServer = new HttpServer(8081);
            httpServer.run();
        } catch (Exception e) {
            logger.error("", e);
        }
    }
}
