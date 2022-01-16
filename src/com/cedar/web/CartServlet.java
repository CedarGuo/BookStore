package com.cedar.web;

import com.cedar.pojo.Book;
import com.cedar.pojo.Cart;
import com.cedar.pojo.CartItem;
import com.cedar.service.impl.BookService;
import com.cedar.service.impl.BookServiceImpl;
import com.cedar.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.util.HashMap;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-12-02 22:17
 * @name JavaWeb
 */
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 加购
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.从request域中接受id参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
//                2.调用bookService.getBookById得到book参数
        Book book = bookService.queryBookById(id);
//                3.根据book，得到要添加的cartItem
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
//                4.判断session域中有无cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
//                若无：新建cart对象，并存入session域中，调用cart.addItem(CartItem)方法完成加购
            cart = new Cart();

            req.getSession().setAttribute("cart",cart);
        }
//                若有：直接调用cart.addItem(CartItem)方法完成加购
        cart.addItem(cartItem);

//                5.将购物车的商品数量（session域中得到）、刚添加的图书名回显在当前页面
        req.getSession().setAttribute("lastName",cartItem.getName());
//        6.重定向会发起请求时的页面
        resp.sendRedirect(req.getHeader("referer"));
    }

    /**
     * 处理加购的ajax请求，局部更新信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.从request域中接受id参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
//                2.调用bookService.getBookById得到book参数
        Book book = bookService.queryBookById(id);
//                3.根据book，得到要添加的cartItem
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
//                4.判断session域中有无cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
//                若无：新建cart对象，并存入session域中，调用cart.addItem(CartItem)方法完成加购
            cart = new Cart();

            req.getSession().setAttribute("cart",cart);
        }
//                若有：直接调用cart.addItem(CartItem)方法完成加购
        cart.addItem(cartItem);

//                5.将购物车的商品数量（session域中得到）、刚添加的图书名回显在当前页面
        req.getSession().setAttribute("lastName",cartItem.getName());
//        6.将数据存入map<key,value>，以json格式发送
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String s = gson.toJson(resultMap);

        resp.getWriter().write(s);
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.从request域中接受id参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
//        2.从session域获取cart属性
        Cart cart = (Cart) req.getSession().getAttribute("cart");
//        3.调用cart.deleteItemById(id)删除商品
        cart.deleteItemById(id);
//        4.重定向回原来页面
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void modifyItemCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.从request域中接受id参数，count参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

//        2.从session域获取cart属性
        Cart cart = (Cart) req.getSession().getAttribute("cart");
//        3.调用cart.modifyItemCount(id,count)修改商品数量
        cart.modifyItemCount(id,count);
//        4.重定向回原来页面
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clearItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clearCart();
        resp.sendRedirect(req.getHeader("referer"));

    }

    /**
     * 实现分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
