package com.bnuz.test;

import com.bnuz.pojo.Book;
import com.bnuz.pojo.Page;
import com.bnuz.service.BookService;
import com.bnuz.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"国哥在手，天下我有！", "1125", new BigDecimal(1000000),100000000, 0, null));
    }
    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }
    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"社会我国哥，人狠话不多！", "1125", new BigDecimal(999999),10, 111110, null));
    }
    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }
    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
}