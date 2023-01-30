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
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-05-12-12:47
 */
public class ListProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 创建service层对象
        ProductService service = new ProductServiceImpl();
        // 2 调用service层用于查询所有Client的getAll方法
        List<Product> products = service.getAll();
        // 3 将查询出的所有Client放进request域当中
        req.setAttribute("products",products);
        // 4 将请求转发到index.jsp
        req.getRequestDispatcher("/productList.jsp").forward(req,resp);
    }
}
