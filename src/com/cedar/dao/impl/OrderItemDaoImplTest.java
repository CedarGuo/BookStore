package com.cedar.dao.impl;

import com.alibaba.druid.util.JdbcUtils;
import com.cedar.pojo.OrderItem;
import com.cedar.utils.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-12-04 10:37
 * @name JavaWeb
 */
public class OrderItemDaoImplTest {
    private OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        Connection conn = JDBCUtils.getConnection();
        OrderItem orderItem = new OrderItem(null, "huaer", 1, new BigDecimal(30), new BigDecimal(30), "rqwn");
        OrderItem orderItem1 = new OrderItem(null, "huahudie", 1, new BigDecimal(30), new BigDecimal(30), "rtrt");

        orderItemDao.saveOrderItem(conn,orderItem);
        orderItemDao.saveOrderItem(conn,orderItem1);
        JDBCUtils.closeConnection(conn);
    }

    @Test
    public void queryOrderItemsByOrderId() {
        Connection conn = JDBCUtils.getConnection();
        List<OrderItem> rqwn = orderItemDao.queryOrderItemsByOrderId(conn, "rtrt");
        rqwn.forEach(System.out::println);
        JDBCUtils.closeConnection(conn);

    }
}