package com.cedar.dao.impl;

import com.cedar.pojo.Book;
import com.cedar.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-29 17:46
 * @name JavaWeb
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public int addBook(Connection conn,Book book) {
        String sql = "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        int update = update(conn, sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
        return update;
    }

    @Override
    public int deleteBookById(Connection conn, Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(conn,sql,id);
    }

    @Override
    public int updateBook(Connection conn, Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        return update(conn,sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Connection conn, Integer id) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where id = ?";
        Book book = queryForOne(conn, sql, id);
        return book;
    }

    @Override
    public List<Book> queryBooks(Connection conn) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book";
        List<Book> books = queryForList(conn, sql);
        return books;
    }

    @Override
    public Integer queryForPageTotalCount(Connection conn) {
        String sql = "select count(*)  from t_book";
        //Number是所有包装类的父类，sql查询的结果是long类型的基本数据类型变量，无法直接转成Integer类型
        Number value = (Number)getForSingleValue(conn, sql);
        return value.intValue();
    }

    @Override
    public List<Book> queryForPageItems(Connection conn, int begin, int pageSize) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book limit ?,?";
        List<Book> books = queryForList(conn, sql, begin, pageSize);
        return books;
    }

    @Override
    public Integer queryForPageTotalCount(Connection conn, int min, int max) {
        String sql = "select count(*)  from t_book where price between ? and ?";
        //Number是所有包装类的父类，sql查询的结果是long类型的基本数据类型变量，无法直接转成Integer类型
        Number value = (Number)getForSingleValue(conn, sql, min, max);
        return value.intValue();
    }

    @Override
    public List<Book> queryForPageItems(Connection conn, int min, int max, int begin, int pageSize) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where price between ? and ? limit ?,?";
        List<Book> books = queryForList(conn, sql, min, max, begin, pageSize);
        return books;
    }

}
