package com.bnuz.web;

import com.bnuz.pojo.Book;
import com.bnuz.pojo.Cart;
import com.bnuz.pojo.CartItem;
import com.bnuz.service.BookService;
import com.bnuz.service.impl.BookServiceImpl;
import com.bnuz.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cartServlet")
public class CartServlet extends BaseServlet{
    private BookService bookService =new BookServiceImpl();
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());// 调用 Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        System.out.println("请求头 Referer 的值：" + req.getHeader("Referer"));
        req.getSession().setAttribute("lastName",cartItem.getName());
// 重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
// 获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
// 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
// 删除 了购物车商品项
            cart.deleteItem(id);
// 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
// 1 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
// 清空购物车
            cart.clear();
// 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
// 获取请求的参数 商品编号 、商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
// 获取 Cart 购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
// 修改商品数量
            cart.updateCount(id, count);
// 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
