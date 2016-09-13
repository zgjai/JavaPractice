package practice.H2DataBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by zhangguijiang on 16/7/30.
 */
public class H2Connector implements Connector{

    private static final Logger logger = LoggerFactory.getLogger(H2Connector.class);
    private static final String DRIVER = "org.h2.Driver";

    private Connection connection;
    private Statement statement;

    public void connect(String url, String userName, String password) {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
        }catch (Exception e){
            logger.error("h2 connect err", e);
        }
    }

    public boolean execute(String sql) {
        try {
            return statement.execute(sql);
        }catch (Exception e){
            logger.error("h2 execute err", e);
            return false;
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            return statement.executeQuery(sql);
        }catch (Exception e){
            logger.error("h2 execute err", e);
            return null;
        }
    }

    public void close() {
        try {
            statement.close();
            connection.close();
        }catch (Exception e){
            logger.error("h2 close err", e);
        }
    }
}
