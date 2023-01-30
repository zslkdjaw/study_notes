package com.bnuz.service.impl;

import com.bnuz.dao.BookDao;
import com.bnuz.dao.impl.BookDaoImpl;
import com.bnuz.pojo.Book;
import com.bnuz.pojo.Page;
import com.bnuz.service.BookService;
import com.bnuz.web.BaseServlet;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }
    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }
    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }
    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }
    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize >0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        int begin = (page.getPageNo()-1) * pageSize;
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);

        return page;
    }
}
