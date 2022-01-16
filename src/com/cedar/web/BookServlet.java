package com.cedar.web;

import com.cedar.pojo.Book;
import com.cedar.pojo.Page;
import com.cedar.service.impl.BookServiceImpl;
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
 * @create 2021-11-29 22:27
 * @name JavaWeb
 */
public class BookServlet extends BaseServlet {
    private BookServiceImpl bookService = new BookServiceImpl();

    /**
     * 添加图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、数据注入
        Book book = new Book();
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);

        book = WebUtils.copyParamToBean(req.getParameterMap(),book);

//        2、调用service.add方法添加图书
        bookService.addBook(book);

//        3、重定向到book_manager.jsp页面(要先经过bookServlet?action=list吗？重定向的斜杠/只解析到 ip:port/)
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
    }

    /**
     * 删除图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、获得图书id，解析失败时设置id为0
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
//        获得当前页面，不存在时返回首页
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
//        2、调用bookService的delete方法处理业务
        bookService.deleteBookById(id);
//        3、重定向到book_manager.jsp
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    /**
     * 修改图书（需传入id参数）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.从req域中获取表单提交的数据并注入javaBean
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);

//        2.调用service.update(book)处理业务
        bookService.updateBook(book);
//        3.重定向到图书列表页面 /book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    /**
     * 用于/manager/book_manager图书管理页面显示
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //1.调用service方法得到books
        List<Book> books = bookService.queryBooks();
        //2.保存到request域中
        req.setAttribute("books",books);
        //3、请求转发到/pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /**
     * 获取图书，并请求转发到book_edit.jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1.从request域中得到id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
//        2.调用bookService.queryBookById(id)
        Book book = bookService.queryBookById(id);
//        3.把book存入request域中
        req.setAttribute("book",book);
//        4.请求转发到book_edit.jsp，确保是同一次请求，request域的数据才能共享，而重定向是两次请求，不共享requset域
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
//        resp.sendRedirect(req.getContextPath() + "/pages/manager/book_edit.jsp");
    }

    /**
     * 输入当前页码 长度 ，得到图书分页信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        1.接受request中的参数, pageNo（默认是第一页）, pageSize（默认是Page类中的PAGE_SIZE）
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
//        2.调用bookService.page()得到当前页数据
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
//        3.将page数据传入request域中
        req.setAttribute("page",page);
//        4.请求转发到/manager/book_manager.jsp，让前端book_manager.jsp能获得request域数据
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
