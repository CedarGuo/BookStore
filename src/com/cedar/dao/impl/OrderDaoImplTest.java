package com.cedar.dao.impl;

import com.cedar.pojo.Order;
import com.cedar.utils.JDBCUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-12-03 22:43
 * @name JavaWeb
 */
public class OrderDaoImplTest {
    private OrderDaoImpl orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        Connection conn = JDBCUtils.getConnection();

        Order order = new Order("rqn", new Timestamp(System.currentTimeMillis()), new BigDecimal(30), 0, 2);
        orderDao.saveOrder(conn,order);

        JDBCUtils.closeConnection(conn);
    }

    @Test
    public void queryOrders() {
        Connection conn = JDBCUtils.getConnection();

        List<Order> orders = orderDao.queryOrders(conn);
        orders.forEach(System.out::println);
        JDBCUtils.closeConnection(conn);

    }

    @Test
    public void queryOrderByOrderId() {

    }

    @Test
    public void changeOrderStatus() {
        Connection conn = JDBCUtils.getConnection();
        orderDao.changeOrderStatus(conn,"rtrt",1);
        System.out.println(orderDao.queryOrderByOrderId(conn,"rtrt"));
        JDBCUtils.closeConnection(conn);

    }

    @Test
    public void queryOrdersByUserId() {
        Connection conn = JDBCUtils.getConnection();
        List<Order> orders = orderDao.queryOrdersByUserId(conn, 1);
        orders.forEach(System.out::println);
        JDBCUtils.closeConnection(conn);

    }
}