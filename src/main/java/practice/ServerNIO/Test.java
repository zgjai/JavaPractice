package practice.ServerNIO;

import java.util.Scanner;

/**
 * Created by zhangguijiang on 16/7/3.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Server.start();
        Thread.sleep(100);
        Client.start();
        while (Client.sendMsg(new Scanner(System.in).nextLine()))
            ;
    }
}
