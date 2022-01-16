package com.cedar.service.impl;

import com.cedar.dao.impl.*;
import com.cedar.pojo.*;
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
 * @create 2021-12-04 23:55
 * @name JavaWeb
 */
public class OrderServiceImplTest {
private OrderServiceImpl orderService = new OrderServiceImpl();
private OrderDao orderDao = new OrderDaoImpl();
private OrderItemDao orderItemDao = new OrderItemDaoImpl();
private BookDao bookDao = new BookDaoImpl();
    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到放弃", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));

        String orderId = orderService.createOrder(cart, 1);


        Connection conn = JDBCUtils.getConnection();

        Order order = orderDao.queryOrderByOrderId(conn, orderId);
        System.out.println(order);

        System.out.println();

        List<OrderItem> orderItems = orderItemDao.queryOrderItemsByOrderId(conn, orderId);
        orderItems.forEach(System.out::println);

        System.out.println();

        Book book1 = bookDao.queryBookById(conn, 1);
        Book book2 = bookDao.queryBookById(conn, 2);
        System.out.println(book1);
        System.out.println(book2);
    }
}