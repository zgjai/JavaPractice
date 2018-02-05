package LintCode.SystemDesign.ConsistentHashing_TinyUrl;

/**
 * Created by zhangguijiang on 2017/3/21.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 1. 将 360° 的区间分得更细。从 0~359 变为一个 0 ~ n-1 的区间，将这个区间首尾相接，连成一个圆。
 *
 * 2. 当加入一台新的机器的时候，随机选择在圆周中撒 k 个点，代表这台机器的 k 个 micro-shards。
 *
 * 3. 每个数据在圆周上也对应一个点，这个点通过一个 hash function 来计算。
 *
 * 4. 一个数据该属于那台机器负责管理，是按照该数据对应的圆周上的点在圆上顺时针碰到的第一个 micro-shard 点所属的机器来决定。
 * 
 * n 和 k在真实的 NoSQL 数据库中一般是 2^64 和 1000。
 *
 * 请实现这种引入了 micro-shard 的 consistent hashing 的方法。主要实现如下的三个函数：
 *
 * create(int n, int k)
 * 
 * addMachine(int machine_id) // add a new machine, return a list of shard ids.
 *
 * getMachineIdByHashCode(int hashcode) // return machine id
 */
public class ConsistentHashingII {

    private int n;
    private int k;
    private List<Integer> vIdList = new ArrayList<Integer>();
    private Map<Integer, Integer> vMMap = new HashMap<Integer, Integer>();

    // @param n a positive integer
    // @param k a positive integer
    // @return a Solution object
    public static ConsistentHashingII create(int n, int k) {
        // Write your code here
        ConsistentHashingII consistentHashing = new ConsistentHashingII();
        consistentHashing.n = n;
        consistentHashing.k = k;
        return consistentHashing;
    }

    // @param machine_id an integer
    // @return a list of shard ids
    public List<Integer> addMachine(int machine_id) {
        // Write your code here
        List<Integer> newIdList = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            int newN = randomNum();
            vMMap.put(newN, machine_id);
            newIdList.add(newN);
            vIdList.add(newN);
        }
        Collections.sort(vIdList);
        return newIdList;
    }

    // @param hashcode an integer
    // @return a machine id
    public int getMachineIdByHashCode(int hashcode) {
        // Write your code here
        if (vIdList.size() == 0) {
            return -1;
        }
        int id = getSuitInt(hashcode);
        return vMMap.get(id);
    }

    private Integer randomNum() {
        Random random = new Random();
        while (true) {
            Integer i = random.nextInt(n);
            if (!vIdList.contains(i)) {
                return i;
            }
        }
    }

    private int getSuitInt(int hashcode) {
        int suId = -1;
        for (int i = 0; i < vIdList.size(); i++) {
            if (hashcode <= vIdList.get(i)) {
                return vIdList.get(i);
            }
            if (i == vIdList.size() - 1) {
                if (hashcode <= vIdList.get(i)) {
                    return vIdList.get(i);
                } else {
                    return vIdList.get(0);
                }
            }
            if (hashcode > vIdList.get(i) && hashcode < vIdList.get(i + 1)) {
                return vIdList.get(i + 1);
            }
        }
        return suId;
    }
}
