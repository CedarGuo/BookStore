package com.cedar.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-26 9:47
 * @name JavaWeb
 */
public class JDBCUtils {

    private static DruidDataSource dataSource = null;
    //给当前线程（在tomcat中一次请求从发起到结束就是一个线程）关联connection对象
    public static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


    static  {
        try {
            Properties properties = new Properties();

            InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");

            properties.load(resourceAsStream);

            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 从threadLocal对象中获取关联的连接，如果没有设置，则从DataSource中获取然后设置
     * 并关闭数据库自动提交
     * @return
     */
    public static Connection getConnection(){
        Connection conn = threadLocal.get();
        if (conn == null){

            try {
                conn = dataSource.getConnection();
                threadLocal.set(conn);
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return conn;
    }

    /**
     * 获取当前线程关联的数据库连接，
     * 以线程为单位 提交 事务，
     * 恢复连接自动提交设置，
     * 把连接归还到连接池，
     * 并移除当前线程关联的连接。
     * 因为线程要回到线程池重复利用，如果不移除，则下次threadLocal.get得到的conn地址已经被关闭，会报异常。
     */
    public static void commitAndClose(){
        Connection conn = threadLocal.get();

        if (conn != null){
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtils.recoverAutoCommit(conn);
                JDBCUtils.closeConnection(conn);
            }
        }
// 一旦执行commit操作，就说明 前面整个事务 都没有抛出异常，也就不会再执行rollback了，
// 至于commit自身的异常，只有当数据库连接不存在或者 autocommit=true时才会抛出，
// 而当执行到这一步中断连接时，rollback本来就是无法获得conn的。
// 不用担心 remove之后 rollback无法获得conn的问题，因为根本就不会执行。
        threadLocal.remove();
    }

    /**
     * 获取当前线程关联的数据库连接，
     * 以线程为单位 回滚 事务，
     * 恢复连接自动提交设置，
     * 把连接归还到连接池，
     * 并移除当前线程关联的连接。
     */
    public static void rollbackAndClose(){
        Connection conn = threadLocal.get();
        if (conn != null){
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtils.recoverAutoCommit(conn);
                JDBCUtils.closeConnection(conn);
            }
        }
        threadLocal.remove();
    }

    /**
     * 恢复数据库连接的自动提交
     * @param conn
     */
    public static void recoverAutoCommit(Connection conn){

        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接，放回数据库连接池
     * @param conn
     */
    public static void closeConnection(Connection conn){
        DbUtils.closeQuietly(conn);

    }

    public static void closeConnection(Connection conn, Statement ps){
        DbUtils.closeQuietly(conn);
        DbUtils.closeQuietly(ps);

    }

    public static void closeConnection(Connection conn, Statement ps, ResultSet rs){
        DbUtils.closeQuietly(conn);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(rs);

    }

    public static DruidDataSource getDataSource() {
        return dataSource;
    }
}
