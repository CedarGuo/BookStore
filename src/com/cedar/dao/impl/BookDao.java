package com.cedar.dao.impl;

import com.cedar.pojo.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-29 17:42
 * @name JavaWeb
 */
public interface BookDao {
    /**
     * 添加图书
     * @param book
     * @return 如果返回0则添加失败
     */
    public int addBook(Connection conn, Book book);

    /**
     * 删除指定id的图书
     * @param id
     * @return
     */
    public int deleteBookById(Connection conn,Integer id);

    /**
     * 修改图书内容(sql语句按照id进行查找)
     * @param book
     * @return
     */
    public int updateBook(Connection conn,Book book);

    /**
     * 查询指定id的图书
     * @param id
     * @return
     */
    public Book queryBookById(Connection conn,Integer id);

    /**
     * 查询图书列表
     * @return
     */
    public List<Book> queryBooks(Connection conn);

    /**
     * 查询图书总数量
     * @return
     */
    public Integer queryForPageTotalCount(Connection conn) ;

    /**
     * 查询指定 起始位置 和 数量 的book列表
     * @param begin
     * @param pageSize
     * @return
     */
    public List<Book> queryForPageItems(Connection conn, int begin, int pageSize);

    /**
     * 查询价格在min-max之间的图书总数量
     * @param conn
     * @param min
     * @param max
     * @return
     */
    public  Integer queryForPageTotalCount(Connection conn,int min,int max);

    /**
     * 从价格在min-max之间的图书中，查询指定 起始位置 和 数量 的book列表
     * @param conn
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    public List<Book> queryForPageItems(Connection conn,int min,int max, int begin, int pageSize);

}
