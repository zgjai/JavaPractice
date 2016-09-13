package practice.H2DataBase;

import java.sql.ResultSet;

/**
 * Created by zhangguijiang on 16/7/30.
 */
public interface Connector {

    void connect(String url, String userName, String password);

    boolean execute(String sql);

    ResultSet executeQuery(String sql);

    void close();
}
