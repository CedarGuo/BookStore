package com.cedar.service.impl;

import com.cedar.pojo.Cart;
import com.cedar.pojo.Order;
import com.cedar.pojo.OrderItem;
import javafx.scene.shape.VLineTo;

import java.util.List;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-12-04 10:53
 * @name JavaWeb
 */
public interface OrderService {

    /**
     * 根据购物车和userId创建订单 和 订单详情项，并存入数据库，且修改对应的商品销量和库存
     * @param cart
     * @param userId
     * @return
     */
    public String createOrder(Cart cart,Integer userId);

    /**
     * 用于查询全部订单
     * @return
     */
    public List<Order> showAllOrders();

    /**
     * 根据订单号，查询订单详情
     * @param orderId
     * @return
     */
    public List<OrderItem> showOrderDetail(String orderId);

    /**
     * 根据订单号，改变订单状态 未发货-》已发货 -》已签收
     * @param orderId
     * @param status
     */
    public void changeOrderStatus(String orderId,int status);

    /**
     * 根据userId，查询订单
     * @param userId
     */
    public List<Order> showMyOrders(int userId);
}
