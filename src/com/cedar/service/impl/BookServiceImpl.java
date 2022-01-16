package com.cedar.service.impl;

import com.cedar.dao.impl.BookDaoImpl;
import com.cedar.pojo.Book;
import com.cedar.pojo.Page;
import com.cedar.utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-29 21:10
 * @name JavaWeb
 */
public class BookServiceImpl implements BookService {

    private BookDaoImpl bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {

        Connection conn = JDBCUtils.getConnection();
        bookDao.addBook(conn,book);
    }

    @Override
    public void deleteBookById(Integer id) {

        Connection conn = JDBCUtils.getConnection();
        bookDao.deleteBookById(conn,id);

    }

    @Override
    public void updateBook(Book book) {

        Connection conn = JDBCUtils.getConnection();
        bookDao.updateBook(conn,book);

    }

    @Override
    public Book queryBookById(Integer id) {

        Connection conn = JDBCUtils.getConnection();
        Book book = bookDao.queryBookById(conn, id);
        return book;

    }

    @Override
    public List<Book> queryBooks() {

        Connection conn = JDBCUtils.getConnection();
        List<Book> books = bookDao.queryBooks(conn);
        return books;

    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> bookPage = new Page<>();

        Connection conn = JDBCUtils.getConnection();



        // 设置每页显示的数量
        bookPage.setPageSize(pageSize);

        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount(conn);
        bookPage.setPageTotalCount(pageTotalCount);

        // 求总页码
        double doublePageSize = (double)pageSize;
        Integer pageTotal = (int)Math.ceil(pageTotalCount/doublePageSize);
        bookPage.setPageTotal(pageTotal);

        // 设置当前页码,为了在set中设置上界，该步骤必须放在setPageTotal后面
        bookPage.setPageNo(pageNo);

        // 求当前页数据,pageNo在set方法中进行了边界处理
        List<Book> items = bookDao.queryForPageItems(conn, (bookPage.getPageNo() - 1) * pageSize, pageSize);
        bookPage.setItems(items);

        return bookPage;
    }

    @Override
    public Page<Book> page(int min, int max, int pageNo, int pageSize) {
        Page<Book> bookPage = new Page<>();

        Connection conn = JDBCUtils.getConnection();

        // 设置每页显示的数量
        bookPage.setPageSize(pageSize);

        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount(conn,min,max);
        bookPage.setPageTotalCount(pageTotalCount);

        // 求总页码
        double doublePageSize = (double)pageSize;
        Integer pageTotal = (int)Math.ceil(pageTotalCount/doublePageSize);
        bookPage.setPageTotal(pageTotal);

        // 设置当前页码,为了在set中设置上界，该步骤必须放在setPageTotal后面
        bookPage.setPageNo(pageNo);

        // 求当前页数据,pageNo在set方法中进行了边界处理
        List<Book> items = bookDao.queryForPageItems(conn, min, max, (bookPage.getPageNo() - 1) * pageSize, pageSize);
        bookPage.setItems(items);

        return bookPage;
    }
}
