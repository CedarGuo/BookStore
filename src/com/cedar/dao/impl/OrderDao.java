package com.cedar.dao.impl;

import com.cedar.pojo.Order;

import java.sql.Connection;
import java.util.List;

/**
 * @author Cedar
 * @version {}
 * @description 用于Order相关的增删改查
 * @create 2021-12-03 22:17
 * @name JavaWeb
 */
public interface OrderDao {
    /**
     * 保存订单
     * @param order
     */
    public void saveOrder(Connection conn,Order order);

    /**
     * 查询全部订单
     * @return
     */
    public List<Order> queryOrders(Connection conn);

    /**
     * 根据订单号查询
     * @param conn
     * @param orderId
     * @return
     */
    public Order queryOrderByOrderId(Connection conn, String orderId);

    /**
     * 将id为orderId的发货状态修改为status
     * @param orderId
     * @param status
     */
    public void changeOrderStatus(Connection conn, String orderId,int status);

    /**
     * 查询指定id用户的全部订单
     * @param userId
     * @return
     */
    public List<Order> queryOrdersByUserId(Connection conn, int userId);
}
