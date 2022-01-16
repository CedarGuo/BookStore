package com.cedar.dao.impl;

import com.cedar.pojo.Order;

import java.sql.Connection;
import java.util.List;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-12-03 22:25
 * @name JavaWeb
 */
public class OrderDaoImpl extends BaseDao <Order> implements OrderDao {

    @Override
    public void saveOrder(Connection conn, Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        update(conn,sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());

    }

    @Override
    public List<Order> queryOrders(Connection conn) {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order";
        List<Order> orders = queryForList(conn, sql);
        return orders;
    }

    @Override
    public Order queryOrderByOrderId(Connection conn, String orderId) {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order where order_id=?";
        Order order = queryForOne(conn, sql, orderId);
        return order;
    }

    @Override
    public void changeOrderStatus(Connection conn, String orderId, int status) {
        String sql = "update t_order set `status` = ? where `order_id`= ?";
        update(conn,sql,status,orderId);
    }

    @Override
    public List<Order> queryOrdersByUserId(Connection conn, int userId) {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order where `user_id` = ?";
        List<Order> orders = queryForList(conn, sql, userId);
        return orders;
    }
}
