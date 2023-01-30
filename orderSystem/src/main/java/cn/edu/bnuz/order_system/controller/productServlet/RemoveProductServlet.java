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
 * @create 2022-05-12-13:44
 */
public class RemoveProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Id = req.getParameter("productId");

        int id = Integer.parseInt(Id);


        //2 创建 Service对象
        ProductService service = new ProductServiceImpl();
        //3 调用 clientService 方法
        service.removeProduct(new Product(id));

        resp.sendRedirect(req.getContextPath()+"/listProduct");
    }
}

