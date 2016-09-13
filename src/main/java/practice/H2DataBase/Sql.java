package practice.H2DataBase;

/**
 * Created by zhangguijiang on 16/7/30.
 */
public class Sql {

    public static final String CREATE_TABLE_SQL = "create table %s (%s);";

    public static final String SELECT_SQL = "select %s from %s";

    public static final String SELECT_WITH_WHERE_SQL = "select %s from %s where %s";

    public static final String INSERT_SQL = "insert into %s values %s";

    public static final String DELETE_WITH_WHERE_SQL = "delete from %s where %s";

    public static final String UPDATE_WITH_WHERE_SQL = "update %s set %s where %s";
}
