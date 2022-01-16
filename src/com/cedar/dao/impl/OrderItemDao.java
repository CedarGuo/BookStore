package com.cedar.dao.impl;

import com.cedar.pojo.Order;
import com.cedar.pojo.OrderItem;

import java.sql.Connection;
import java.util.List;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-12-04 10:22
 * @name JavaWeb
 */
public interface OrderItemDao {
    /**
     * 保存orderItem
     * @param orderItem
     */
    public void saveOrderItem(Connection conn, OrderItem orderItem);

    /**
     * 查询指定订单号的订单明细 列表
     * @param conn
     * @param orderId
     * @return
     */
    public List<OrderItem> queryOrderItemsByOrderId(Connection conn, String orderId);
}
