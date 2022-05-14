package com.study.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookDaoTestCase {

    @Autowired
    private  BookDao bookDao;

    @Test
    void testGetById(){
        System.out.println(bookDao.selectById(1));
    }

    @Test
    void testSave(){
        Book book = new Book();
        book.setType("TestData 123");
        book.setName("TestData 123");
        book.setDescription("TestData 123");
        bookDao.insert(book);

    }
    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(13);
        book.setType("UpdatedData 456");
        book.setName("UpdatedData 456");
        book.setDescription("UpdatedData 456");
        bookDao.updateById(book);
    }
    @Test
    void testDelete(){
        bookDao.deleteById(13);

    }
    @Test
    void testGetAll(){
        bookDao.selectList(null);
    }
    @Test
    void testGetPage(){
        IPage page = new Page(2,5);
        IPage pp = bookDao.selectPage(page,null);

    }
    @Test
    void testGetBy(){
        //select * from tbl_book where name like %spring%
        QueryWrapper<Book> qw = new QueryWrapper<>();
        qw.like("name","Spring");
        bookDao.selectList(qw);
    }
    @Test
    void testGetBy2(){
        String name ="1";
        //select * from tbl_book where name like %spring%
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null,Book::getName,name);
        bookDao.selectList(lqw);
    }

    @Test
    void testGetBy3(){
        String name ="Spring";
        IPage page = new Page(1,10);
        //select * from tbl_book where name like %spring%
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null,Book::getName,name);
        bookDao.selectPage(page,lqw);
    }
}
