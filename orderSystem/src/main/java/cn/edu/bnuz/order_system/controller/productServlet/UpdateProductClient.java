package cn.edu.bnuz.order_system.controller.productServlet;

import cn.edu.bnuz.order_system.entity.Product;
import cn.edu.bnuz.order_system.service.ProductService;
import cn.edu.bnuz.order_system.service.impl.ProductServiceImpl;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zhang Hao
 * @create 2022-05-12-13:52
 */
public class UpdateProductClient extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        int id = Integer.parseInt(productId);

        String productName = new String(req.getParameter("productName").getBytes("ISO-8859-1"),"utf-8");

        String price = req.getParameter("price");
        int pr = price.equals("")?-1:Integer.parseInt(price);

        String stock = req.getParameter("stock");
        int st = stock.equals("")?-1:Integer.parseInt(stock);
        ProductService service = new ProductServiceImpl();

        service.updateProduct(new Product(id,productName,pr,st));

        resp.sendRedirect(req.getContextPath()+"/listProduct");
    }
}
