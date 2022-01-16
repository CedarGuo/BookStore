package com.cedar.test;


import com.cedar.utils.JDBCUtils;

import java.sql.Connection;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-26 10:35
 * @name JavaWeb
 */
public class JDBCUtilsTest {


    @org.junit.Test
    public void getConnection() {
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
        JDBCUtils.closeConnection(connection);
        JDBCUtils.getDataSource().close();
    }

    @org.junit.Test
    public void closeConnection() {
        System.out.println("获取连接！");
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
        System.out.println("尝试关闭连接");
        JDBCUtils.closeConnection(connection);
        System.out.println("归还数据库连接池！");
    }

    @org.junit.Test
    public void closeConnection1() {
    }
}
