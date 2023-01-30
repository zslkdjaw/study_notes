package com.study.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceTestCase {

    @Autowired
    private BookService bookService;

    @Test
    void testGetById(){
        System.out.println(bookService.getById(4));
    }
    @Test
    void testSave(){
        Book book = new Book();
        book.setType("TestData 123");
        book.setName("TestData 123");
        book.setDescription("TestData 123");
        bookService.save(book);

    }
    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(13);
        book.setType("UpdatedData abc");
        book.setName("UpdatedData abc");
        book.setDescription("UpdatedData bca");
        bookService.update(book);
    }
    @Test
    void testDelete(){
        bookService.delete(15);

    }
    @Test
    void testGetAll(){
        bookService.getAll();
    }
    @Test
    void testGetPage(){

        IPage page = bookService.getPage(2,5);
    }


}
