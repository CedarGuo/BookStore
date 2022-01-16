package com.cedar.service.impl;

import com.alibaba.druid.util.JdbcUtils;
import com.cedar.dao.impl.BookDao;
import com.cedar.dao.impl.BookDaoImpl;
import com.cedar.dao.impl.OrderDaoImpl;
import com.cedar.dao.impl.OrderItemDaoImpl;
import com.cedar.pojo.*;
import com.cedar.utils.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;

import javax.sql.rowset.JdbcRowSet;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-12-04 11:00
 * @name JavaWeb
 */
public class OrderServiceImpl implements OrderService {
    private OrderDaoImpl orderDao = new OrderDaoImpl();
    private OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        Connection conn = JDBCUtils.getConnection();

        //1.得到orderId
        String orderId = System.currentTimeMillis() + "" + userId;
        //2.cart+userId->Order对象，orderItems对象列表的添加
        Order order = new Order(orderId, new Timestamp(System.currentTimeMillis()), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(conn,order);


        //3.cart+orderId->orderItems对象列表, orderItems对象列表的添加
        for (Map.Entry<Integer,CartItem> entry:cart.getItems().entrySet()
                ) {
            OrderItem orderItem = new OrderItem(null, entry.getValue().getName(), entry.getValue().getCount(), entry.getValue().getPrice(), entry.getValue().getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(conn,orderItem);
//            4.更新数据库中book的库存和销量
            Book book = bookDao.queryBookById(conn, entry.getKey());
            book.setSales(book.getSales()+entry.getValue().getCount());
            book.setStock(book.getStock()-entry.getValue().getCount());
            bookDao.updateBook(conn,book);
        }
        // 5.清空购物车
        cart.clearCart();

        //6.返回订单号
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        Connection conn = JDBCUtils.getConnection();

        List<Order> orders = orderDao.queryOrders(conn);


        return orders;
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        Connection conn = JDBCUtils.getConnection();
        List<OrderItem> orderItems = orderItemDao.queryOrderItemsByOrderId(conn, orderId);

        return orderItems;
    }

    @Override
    public void changeOrderStatus(String orderId, int status) {
        Connection conn = JDBCUtils.getConnection();
        orderDao.changeOrderStatus(conn,orderId,status);

    }

    @Override
    public List<Order> showMyOrders(int userId) {
        Connection conn = JDBCUtils.getConnection();

        List<Order> orders = orderDao.queryOrdersByUserId(conn, userId);


        return orders;
    }


}
