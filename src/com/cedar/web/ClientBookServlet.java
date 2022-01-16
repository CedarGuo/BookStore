package com.cedar.web;

import com.cedar.pojo.Book;
import com.cedar.pojo.Page;
import com.cedar.service.impl.BookServiceImpl;
import com.cedar.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.IOException;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-12-01 20:58
 * @name JavaWeb
 */
public class ClientBookServlet extends BaseServlet {

    private BookServiceImpl bookService = new BookServiceImpl();

    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        1.接受request中的参数, pageNo（默认是第一页）, pageSize（默认是Page类中的PAGE_SIZE）
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
//        2.调用bookService.page()得到当前页数据
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/clientBookServlet?action=page");

//        3.将page数据传入request域中
        req.setAttribute("page",page);
//        4.请求转发到/manager/book_manager.jsp，让前端book_manager.jsp能获得request域数据
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        1.得到request域的参数 min max
//                2.调用bookService.pageByPrice(int min,int max),得到page
//        3.

//        1.接受request中的参数, pageNo（默认是第一页）, pageSize（默认是Page类中的PAGE_SIZE）
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

//        2.调用bookService.page()得到当前页数据
        Page<Book> page = bookService.page(min,max,pageNo, pageSize);
        //主要用于分页条的地址
        //优化：按照价格查询时min max 不全为 null，当min max不为null时才追加min max参数
        StringBuffer buffer = new StringBuffer();
        StringBuffer url= buffer.append("client/clientBookServlet?action=pageByPrice");
        if (req.getParameter("min") != null){
            url.append("&min=").append(min);
        }
        if (req.getParameter("max") != null){
            url.append("&max=").append(max);
        }

        page.setUrl(url.toString());

//        page.setUrl("client/clientBookServlet?action=pageByPrice&min="+min+"&max="+max);

//        3.将page数据传入request域中
        req.setAttribute("page",page);
        req.setAttribute("min",min);
        req.setAttribute("max",max);
//        4.请求转发到/client/index.jsp，让前端index.jsp能获得request域数据
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
