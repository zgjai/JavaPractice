package LintCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangguijiang on 2017/3/17.
 */
public class ConsistentHashing {

    /**
     * 一般的数据库进行horizontal shard的方法是指，把 id 对 数据库服务器总数 n 取模， 然后来得到他在哪台机器上。这种方法的缺点是，当数据继续增加，我们需要增加数据库服务器，将 n 变为 n+1
     * 时，几乎所有的数据都要移动，这就造成了不 consistent。为了减少这种 naive 的 hash方法(%n) 带来的缺陷，出现了一种新的hash算法：一致性哈希的算法——Consistent
     * Hashing。这种算法有很多种实现方式，这里我们来实现一种简单的 Consistent Hashing。
     * 
     * 将 id 对 360 取模，假如一开始有3台机器，那么让3台机器分别负责0~119, 120~239, 240~359 的三个部分。那么模出来是多少，查一下在哪个区间，就去哪台机器。 当机器从 n 台变为 n+1
     * 台了以后，我们从n个区间中，找到最大的一个区间，然后一分为二，把一半给第n+1台机器。
     * 比如从3台变4台的时候，我们找到了第3个区间0~119是当前最大的一个区间，那么我们把0~119分为0~59和60~119两个部分。0~59仍然给第1台机器，60~119给第4台机器。
     * 然后接着从4台变5台，我们找到最大的区间是第3个区间120~239，一分为二之后，变为 120~179, 180~239。 假设一开始所有的数据都在一台机器上，请问加到第 n
     * 台机器的时候，区间的分布情况和对应的机器编号分别是多少？
     */

    /**
     * @param n a positive integer
     * @return n x 3 matrix
     */
    public List<List<Integer>> consistentHashing(int n) {
        // Write your code here
        int mCount = 360;
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        List<Integer> oneM = new ArrayList<Integer>(3);
        oneM.add(0, 0);
        oneM.add(1, 359);
        oneM.add(2, 1);
        resultList.add(oneM);
        if (n == 1) {
            return resultList;
        }
        for (int i = 2; i <= n; i++) {
            resultList = dealAddOne(resultList, i, mCount);
        }
        return resultList;
    }

    private List<List<Integer>> dealAddOne(List<List<Integer>> nowList, int n, int mCount) {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        int max = 0;
        int minM = mCount + 1;
        int index = -1;
        for (int i = 0; i < nowList.size(); i++) {
            List<Integer> item = nowList.get(i);
            int start = item.get(0);
            int end = item.get(1);
            int m = item.get(2);
            if ((end - start > max) || (minM > m && end - start == max)) {
                max = end - start;
                minM = m;
                index = i;
            }
        }
        resultList.addAll(nowList.subList(0, index));
        List<Integer> item = nowList.get(index);
        int start = item.get(0);
        int end = item.get(1);
        int half = start + (end - start) / 2;
        List<Integer> lastOne = new ArrayList<Integer>(3);
        lastOne.add(0, start);
        lastOne.add(1, half);
        lastOne.add(2, item.get(2));
        List<Integer> newOne = new ArrayList<Integer>(3);
        newOne.add(0, half + 1);
        newOne.add(1, end);
        newOne.add(2, n);
        resultList.add(lastOne);
        resultList.add(newOne);
        resultList.addAll(nowList.subList(index + 1, nowList.size()));
        return resultList;
    }

    public static void main(String[] args) {
        ConsistentHashing test = new ConsistentHashing();
        test.consistentHashing(6);
    }
}
