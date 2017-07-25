package DataStructure;

/**
 * Created by zhangguijiang on 2017/6/22.
 */
public interface IteratorOwn<E> {

    boolean hasNext();

    E next();

    default void remove() {throw new UnsupportedOperationException("remove");}


}
