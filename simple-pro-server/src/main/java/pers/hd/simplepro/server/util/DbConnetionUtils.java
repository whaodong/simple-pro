package pers.hd.simplepro.server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB连接工具类
 *
 *@author WangHaoDong
 */
public class DbConnetionUtils {

    private final String username;
    private final String password;
    private final String url;
    /**
     * mysql数据库的驱动类
     **/
    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    /**
     * oracle数据库的驱动类
     **/
    private static final String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
    /**
     * pg数据库的驱动类
     **/
    private static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
    /**
     * hive数据库驱动类
     **/
    private static final String HIVE_DRIVER = "org.apache.hadoop.hive.jdbc.HiveDriver";

    public DbConnetionUtils(String username,
                            String password,
                            String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public Connection testConnection() throws SQLException {
        return DriverManager.getConnection(
                url, username, password);
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean connIsOk(Connection conn) throws SQLException {
        return null != conn && !conn.isClosed();
    }

    public void closeConn(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public enum DatabaseType {
        /**
         * mysql
         **/
        MYSQL,
        /**
         * oracle
         **/
        ORACLE,
        /**
         * postgresql
         **/
        POSTGRESQL,
        /**
         * hive
         **/
        HIVE2
    }
}
