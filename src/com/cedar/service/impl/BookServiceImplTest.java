package com.cedar.service.impl;

import com.cedar.pojo.Book;
import com.cedar.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-29 21:16
 * @name JavaWeb
 */
public class BookServiceImplTest {

    private BookServiceImpl bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"国哥在手，天下我有！", "1125", new BigDecimal(1000000), 100000000, 0, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(23,"社会我国哥，人狠话不多！", "1125", new BigDecimal(999999), 10, 111110, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(23));
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for (Book book:books
             ) {
            System.out.println(book);
        }
    }
    @Test
    public void page(){
        Page<Book> page = bookService.page(50, 100, 1, 4);
        for (Book b:page.getItems()
             ) {
            System.out.println(b);
        }
        System.out.println("totalCount: "+page.getPageTotalCount());
        System.out.println("pageTotal: "+page.getPageTotal());
    }
}