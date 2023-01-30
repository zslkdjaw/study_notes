package com.bnuz.web;

import com.bnuz.pojo.Book;
import com.bnuz.pojo.Page;
import com.bnuz.service.BookService;
import com.bnuz.service.impl.BookServiceImpl;
import com.bnuz.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet("/manager/bookservlet")
public class BookServlet extends BaseServlet{

    private BookService bookService=new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);

        Page<Book> page =bookService.page(pageNo,pageSize);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo=pageNo+1;
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        bookService.addBook(book);
        resp.sendRedirect(req.getContextPath()+"/manager/bookservlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =WebUtils.parseInt(req.getParameter("id"),0);
        bookService.deleteBookById(id);
        resp.sendRedirect(req.getContextPath()+"/manager/bookservlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book=WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath()+"/manager/bookservlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        Book book=bookService.queryBookById(id);
        req.setAttribute("book",book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books=bookService.queryBooks();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
