package com.cedar.dao.impl;

import com.cedar.pojo.OrderItem;

import java.sql.Connection;
import java.util.List;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-12-04 10:26
 * @name JavaWeb
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

    @Override
    public void saveOrderItem(Connection conn, OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        update(conn,sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(Connection conn, String orderId) {
        String sql = "select `id`,`name`,`count`,`price`,`total_price` totalPrice,`order_id` orderId from t_order_item where `order_id`=?";
        List<OrderItem> orderItems = queryForList(conn, sql, orderId);
        return orderItems;
    }
}
