package DataStructure;

/**
 * Created by zhangguijiang on 2017/6/21. list接口，定义list必须要实现的方法
 */
public interface ListOwn<E> {

    // 判断list是否为空
    boolean isEmpty();

    // 获取list长度，超过Integer.MAX_VALUE则返回Integer.MAX_VALUE
    int size();

    // 向list末尾添加一个元素
    void add(E element);

    // 向指定index插入一个元素
    void add(int index, E element);

    // 向list末尾添加集合中所有元素
    void addAll();

    // 获取指定位置的元素
    E get(int index);

    // 删除指定位置的元素
    E remove(int index);
}
