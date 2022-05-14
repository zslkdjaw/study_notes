package com.study.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.study.controller.utils.R;
import com.study.domain.Book;
import com.study.service.impl.BookServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController2 {

    @Autowired
    private BookServiceImpl2 bookService;

    @GetMapping
    public R getAll(){

        return new R(true,bookService.list());
    }

    @PostMapping
    public R save(@RequestBody Book book){
        Boolean flag = bookService.save(book);
        return  new R(flag,flag?"添加成功功":"添加失败败");
    }

    @PutMapping
    public R update(@RequestBody Book book){

        return new R(bookService.modify(book));
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){

        return new R(bookService.delete(id));
    }

    //路径传参  /Books/id
    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        System.out.println("test hot deploy...");

        return new R(true,bookService.getById(id));
    }

//    @GetMapping("{currentPage}/{pageSize}")
//    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize){
//        IPage<Book> page = bookService.getPage(currentPage,pageSize);
//
//        if (currentPage > page.getTotal()){
//            page = bookService.getPage((int) page.getSize(),pageSize);
//        }
//        return new R(true,page);
//    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize,Book book){

        IPage<Book> page = bookService.getPage(currentPage,pageSize,book);

        if (currentPage > page.getTotal()){
            page = bookService.getPage((int) page.getSize(),pageSize,book);
        }
        return new R(true,page);
    }
}
