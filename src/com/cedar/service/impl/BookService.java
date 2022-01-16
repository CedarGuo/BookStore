package com.cedar.service.impl;

import com.cedar.pojo.Book;
import com.cedar.pojo.Page;

import java.util.List;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-29 21:06
 * @name JavaWeb
 */
public interface BookService {
    /**
     * 添加图书
     * @param book
     */
    public void addBook(Book book);

    /**
     * 删除指定id的图书
     * @param id
     */
    public void deleteBookById(Integer id);

    /**
     * 修改图书内容(sql语句按照id进行查找)
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 查询指定id的图书，用于页面回显，因此也要在servlet中需要回传id
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询图书列表
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 返回指定 页码 和 当前页数量 的 Page对象
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Book> page(int pageNo, int pageSize );

    /**
     * 返回在价格区间（min,max）内，指定 页码 和 当前页数量 的 Page对象
     * @param min
     * @param max
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Book> page(int min, int max, int pageNo, int pageSize );


}
