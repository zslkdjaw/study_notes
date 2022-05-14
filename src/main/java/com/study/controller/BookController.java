package com.study.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.study.domain.Book;
import com.study.service.BookService;
import com.study.service.IBookService;
import com.study.service.impl.BookServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookServiceImpl2 bookService;

    @GetMapping
    public List<Book> getAll(){
        return bookService.list();
    }

    @PostMapping
    public  Boolean save(@RequestBody Book book){
        return  bookService.save(book);
    }

    @PutMapping
    public Boolean update(@RequestBody Book book){
        return  bookService.modify(book);
    }

    @DeleteMapping("{id}")
    public Boolean delete(@PathVariable Integer id){
        return bookService.delete(id);
    }

    //路径传参  /Books/id
    @GetMapping("{id}")
    public Book getById(@PathVariable Integer id){
        return bookService.getById(id);
    }

    @GetMapping("{currentPage}/{pageSize}")
    public IPage<Book> getPage(@PathVariable int currentPage,@PathVariable int pageSize){
        return bookService.getPage(currentPage,pageSize);
    }
}
