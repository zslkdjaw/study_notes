package cn.edu.bnuz.order_system.controller.productServlet;

import cn.edu.bnuz.order_system.entity.Client;
import cn.edu.bnuz.order_system.entity.Product;
import cn.edu.bnuz.order_system.service.ClientService;
import cn.edu.bnuz.order_system.service.ProductService;
import cn.edu.bnuz.order_system.service.impl.ClientServiceImpl;
import cn.edu.bnuz.order_system.service.impl.ProductServiceImpl;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zhang Hao
 * @create 2022-05-12-12:52
 */
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1 获取表单数据

        String productId = req.getParameter("productId");
        int id = Integer.parseInt(productId);

        String productName = req.getParameter("productName");

//        new String(req.getParameter("xx").getBytes("ISO-8859-1"),"utf-8");
        String price = req.getParameter("price");
        int pr = Integer.parseInt(price);

        String stock = req.getParameter("stock");
        int st = Integer.parseInt(stock);

        //2 创建 Service对象
        ProductService service = new ProductServiceImpl();

        //3 调用  add方法
        service.addProduct(new Product(id,productName,pr,st));

        resp.sendRedirect(req.getContextPath()+"/listProduct");
    }
}
