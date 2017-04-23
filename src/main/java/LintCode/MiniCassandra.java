package LintCode;

/**
 * Created by zhangguijiang on 2017/3/24.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Cassandra这个数据结构有两个级别的键
 *
 * 1. raw_key 类似于哈希值
 *
 * 2. column_key
 *
 * 3. column_value
 * 
 * 现在要实现下面两个方法：
 * 
 * 1.insert(raw_key, column_key, column_value)
 *
 * 2.query(raw_key, column_start, column_end) 返回一个列表
 */

class Column {
    public int key;
    public String value;

    public Column(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

class ColumnComparator implements Comparator<Column> {
    public int compare(Column o1, Column o2) {
        int result = 0;
        if (o1.key > o2.key) {
            result = 1;
        } else if (o1.key < o2.key) {
            result = -1;
        }
        return result;
    }
}

public class MiniCassandra {

    private Map<String, TreeSet<Column>> map;

    public MiniCassandra() {
        // initialize your data structure here.
        map = new HashMap<String, TreeSet<Column>>();
    }

    /**
     * @param raw_key a string
     * @param column_key an integer
     * @param column_value an integer
     * @return void
     */
    public void insert(String raw_key, int column_key, String column_value) {
        // Write your code here
        TreeSet<Column> result = map.get(raw_key);
        if (result == null) {
            ColumnComparator columnComparator = new ColumnComparator();
            result = new TreeSet<Column>(columnComparator);
        }
        Column column = new Column(column_key, column_value);
        List<Column> columnList = query(raw_key, column_key, column_key);
        if (columnList != null && columnList.size() != 0) {
            result.remove(columnList.get(0));
        }
        result.add(column);
        map.put(raw_key, result);
    }

    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return a list of Columns
     */
    public List<Column> query(String raw_key, int column_start, int column_end) {
        // Write your code here
        List<Column> resultList = new ArrayList<Column>();
        if (column_end < column_start) {
            return resultList;
        }
        TreeSet<Column> kvList = map.get(raw_key);
        if (kvList == null || kvList.size() == 0) {
            return resultList;
        }
        Column start = kvList.first();
        Column end = kvList.last();
        if (end.key < column_start || start.key > column_end) {
            return resultList;
        }
        if (start.key >= column_start && end.key <= column_end) {
            resultList.addAll(kvList);
            return resultList;
        }
        boolean sFlag = false;
        boolean eFlag = true;
        for (Column item : kvList) {
            if (!sFlag) {
                start = item;
                if (item.key >= column_start) {
                    end = item;
                    sFlag = true;
                    eFlag = false;
                }
            }
            if (!eFlag) {
                end = item;
                if (item.key > column_end) {
                    eFlag = true;
                    break;
                }
            }
        }
        resultList.addAll(kvList.subSet(start,sFlag, end, !eFlag));
        return resultList;
    }

    public static void main(String[] args) {
        MiniCassandra cassandra = new MiniCassandra();
        cassandra.insert("google", 1, "haha");
        cassandra.insert("google", 1, "111");
        cassandra.query("google", 0, 1);
    }
}
