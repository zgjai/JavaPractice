package practice.H2DataBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.sql.ResultSet;

/**
 * Created by zhangguijiang on 16/7/30.
 */
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        final String TABLE_NAME = "User";
        final String URL = "jdbc:h2:~/test";
        final String USER_NAME = "sa";
        final String PASSWORD = "";

        Connector h2Connector = new H2Connector();
        h2Connector.connect(URL, USER_NAME, PASSWORD);

        String tableColumns = "Id int primary key, Name varchar(30), Age int, Address varchar(200), Groups varchar(30)";
        String createSql = String.format(Sql.CREATE_TABLE_SQL, TABLE_NAME, tableColumns);
        h2Connector.execute(createSql);

        String insertValues = "(1, 'one', 16, 'beijing', 'base')," + "(2, 'two', 17, 'beijing', 'base'),"
                + "(3, 'three', 18, 'nanjing', 'base')," + "(4, 'four', 19, 'shanghai', 'base'),"
                + "(5, 'five', 20, 'wuhan', 'base')," + "(6, 'six', 21, 'xian', 'base'),"
                + "(7, 'seven', 22, 'chengdu', 'base')," + "(8, 'eight', 23, 'nantong', 'base'),"
                + "(9, 'nine', 24, 'changchun', 'base')," + "(10, 'ten', 25, 'shenyang', 'base')";
        String insertSql = String.format(Sql.INSERT_SQL, TABLE_NAME, insertValues);
        h2Connector.execute(insertSql);

        String selectAge = "age > 20";
        String selectAgeSql = String.format(Sql.SELECT_WITH_WHERE_SQL, "*", TABLE_NAME, selectAge);
        ResultSet result = h2Connector.executeQuery(selectAgeSql);
        try {
            while (result.next()) {
                logger.info("Id = {}, Name = {}, Age = {}, Address = {}, Groups = {}", result.getInt("ID"),
                        result.getString("NAME"), result.getInt("age"), result.getString("address"),
                        result.getString("groups"));
            }
        } catch (Exception e) {
            logger.error("get result err", e);
        }

        String deleteByAge = "age < 18";
        String deleteByAgeSql = String.format(Sql.DELETE_WITH_WHERE_SQL, TABLE_NAME, deleteByAge);
        h2Connector.execute(deleteByAgeSql);

        String updateByAgeWhere = "age > 22";
        String updateByAgeSet = " groups = 'advanced' ";
        String updateByAgeSql = String.format(Sql.UPDATE_WITH_WHERE_SQL, TABLE_NAME, updateByAgeSet, updateByAgeWhere);
        h2Connector.execute(updateByAgeSql);

        String selectAllSql = String.format(Sql.SELECT_SQL, "*", TABLE_NAME);
        result = h2Connector.executeQuery(selectAllSql);
        try {
            while (result.next()) {
                logger.info("Id = {}, Name = {}, Age = {}, Address = {}, Groups = {}", result.getInt("ID"),
                        result.getString("NAME"), result.getInt("age"), result.getString("address"),
                        result.getString("groups"));
            }
        } catch (Exception e) {
            logger.error("get result err", e);
        }

        h2Connector.close();
    }
}
