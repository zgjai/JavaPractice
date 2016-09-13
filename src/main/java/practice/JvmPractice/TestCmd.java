package practice.JvmPractice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangguijiang on 16/7/8.
 */
public class TestCmd {
    public static void main(String[] args){
        List<Integer> list = new ArrayList<Integer>();
        int i = 0;
        while (true){
            list.add(i);
            i++;
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
