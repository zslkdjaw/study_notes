package com.bnuz.dao;

import com.bnuz.pojo.Book;

import java.util.List;

public interface BookDao {

    public int addBook(Book book);
    public int deleteBookById(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    public Integer queryForPageTotalCount();
    public List<Book> queryForPageItems(int begin, int pageSize);
}
