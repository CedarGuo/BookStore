package com.cedar.dao.impl;

import com.cedar.pojo.Book;
import com.cedar.utils.JDBCUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-29 19:18
 * @name JavaWeb
 */
public class BookDaoImplTest {

    private BookDaoImpl bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        Connection conn = JDBCUtils.getConnection();
        bookDao.addBook(conn,new Book(null,"国哥为什么这么帅！", "191125", new BigDecimal(9999),1100000,0,null));
        JDBCUtils.closeConnection(conn);
    }

    @Test
    public void deleteBookById() {
        Connection conn = JDBCUtils.getConnection();
        int i = bookDao.deleteBookById(conn, 21);
        System.out.println(i);
        JDBCUtils.closeConnection(conn);
    }

    @Test
    public void updateBook() {
        Connection conn = JDBCUtils.getConnection();
        int i = bookDao.updateBook(conn, new Book(22, "大家都可以这么帅！", "国哥", new BigDecimal(9999), 1100000, 0, null));
        System.out.println(i);
        JDBCUtils.closeConnection(conn);
    }

    @Test
    public void queryBookById() {
        Connection conn = JDBCUtils.getConnection();
        Book book = bookDao.queryBookById(conn, 22);
        System.out.println(book);
        JDBCUtils.closeConnection(conn);
    }

    @Test
    public void queryBooks() {
        Connection conn = JDBCUtils.getConnection();
        List<Book> books = bookDao.queryBooks(conn);
        for (Book b:books
             ) {
            System.out.println(b);
        }
        JDBCUtils.closeConnection(conn);
    }

    @Test
    public void queryForPageTotalCount(){
        Connection conn = JDBCUtils.getConnection();

        Integer pageTotalCount = bookDao.queryForPageTotalCount(conn,50,100);

        System.out.println(pageTotalCount);

        JDBCUtils.closeConnection(conn);

    }
    @Test
    public void queryForPageItems(){
        Connection conn = JDBCUtils.getConnection();
        List<Book> books = bookDao.queryForPageItems(conn, 50, 100, 0, 4);
        for (Book b:books
             ) {
            System.out.println(b);
        }
        JDBCUtils.closeConnection(conn);

    }
}