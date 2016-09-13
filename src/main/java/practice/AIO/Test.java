package practice.AIO;

import practice.AIO.ClientAIO.Client;
import practice.AIO.ServerAIO.Server;

import java.util.Scanner;

/**
 * Created by zhangguijiang on 16/7/4.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Server.start();
        Thread.sleep(100);
        Client.start();
        System.out.println("请输入请求消息：");
        Scanner scanner = new Scanner(System.in);
        while (Client.sendMsg(scanner.nextLine()))
            ;
    }
}