package practice.JavaGC;

/**
 * Created by zhangguijiang on 16/8/6.
 */
public class Test {
    public static void main(String[] args){
        new Thread(new LogThread()).start();
        new Thread(new GCThread()).start();
    }
}
