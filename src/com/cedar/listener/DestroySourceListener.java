package com.cedar.listener;

import com.cedar.utils.JDBCUtils;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-27 21:40
 * @name JavaWeb
 */
public class DestroySourceListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("webService start");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("webService stop");

        try {
            //关闭JDBC驱动
            while(DriverManager.getDrivers().hasMoreElements()) {
                DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
            }
            System.out.println("jdbc Driver close");

            AbandonedConnectionCleanupThread.shutdown();

            System.out.println("clean thread success");

            JDBCUtils.getDataSource().close();

            System.out.println("Druid DataSource close");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("source close exception!");
        }


    }
}
