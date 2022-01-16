package com.cedar.web;

import com.cedar.pojo.Cart;
import com.cedar.pojo.Order;
import com.cedar.pojo.OrderItem;
import com.cedar.pojo.User;
import com.cedar.service.impl.OrderService;
import com.cedar.service.impl.OrderServiceImpl;
import com.cedar.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-12-05 20:05
 * @name JavaWeb
 */
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();
    /**
     * 用于处理结账请求，生成订单号，并重定向到结算页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.从session域中获得cart对象、user对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        //2.调用orderservice.createorder方法，创建订单，得到订单号，并保存在session域。
        // 如果没有登录，跳转到登录页面，并终止创建订单
        if (user == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        String orderId = orderService.createOrder(cart, user.getId());
//        将订单号保存在session域
        req.getSession().setAttribute("orderId",orderId);
        //3.重定向到checkout.jsp页面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    /**
     * 用于处理管理员的查询订单请求，重定向到order_manager.jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调用orderService的查询方法，得到订单列表orders
        List<Order> orders = orderService.showAllOrders();
        //2.将订单列表orders存入session域，key="orders"
        req.getSession().setAttribute("orders",orders);
        //3.重定向到order_manager.jsp
//        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
        resp.sendRedirect(req.getContextPath() + "/pages/manager/order_manager.jsp");
    }

    /**
     * 用于查询订单详情，请求转发到orderDetail.jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取orderId参数
        String orderId = req.getParameter("orderId");
        //2.调用orderService的查询方法，得到订单项列表orderItems
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        //3.将orderItems存入到request域中,key="orderItems"

        req.setAttribute("orderItems",orderItems);
        //4.请求转发到orderDetail.jsp
        req.getRequestDispatcher("/pages/order/orderDetail.jsp").forward(req,resp);
    }
    /**
     * 订单的发货与签收
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //1.获取status参数\orderId
            String orderId = (String) req.getParameter("orderId");
            int status = WebUtils.parseInt(req.getParameter("status"), 0);
            User user = (User) req.getSession().getAttribute("user");
            //2.调用orderService的修改订单状态方法，修改订单状态
            orderService.changeOrderStatus(orderId,status);

            //3.重定向或请求转发到showAllOrders()方法/showMyOrders()方法显示修改后的结果，（showAllOrders->order_manager.jsp页面）
            if (status == 1){

//            showAllOrders(req,resp);
//            resp.sendRedirect(req.getContextPath() + "/orderServlet?action=showAllOrders");
                req.getRequestDispatcher("/orderServlet?action=showAllOrders").forward(req,resp);
            }
            if (status ==2){
                //签收订单
                req.getRequestDispatcher("/orderServlet?action=showMyOrders").forward(req,resp);
            }
    }

    /**
     * 用户订单查询
     * @param req
     * @param resp
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

            //1.从session域中获取userId
            User user = (User) req.getSession().getAttribute("user");
            if (user == null){
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
                return;
            }
            //2.调用orderService的查询订单方法
            List<Order> orders = orderService.showMyOrders(user.getId());
            //3.将订单列表orders存入session域，key="orders"
            req.getSession().setAttribute("orders",orders);
            //4.重定向到order/order.jsp
            resp.sendRedirect(req.getContextPath() + "/pages/order/order.jsp");
    }


    }
