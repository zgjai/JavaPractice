package practice.GuavaTest;

import com.google.common.base.Splitter;

import java.util.List;

/**
 * Created by zhangguijiang on 2017/2/28.
 */
public class SplitterTest {
    public static void main(String[] args) {
        String test = ",60001159,C1033,A5758E4B-97F4-3062-35DF-8F8C1B22F606,10010";
        List<String> list = Splitter.on(",").trimResults().splitToList(test);
        System.out.println(list);
    }
}
