package cn.edu.bnuz.order_system.controller.orderServlet;

import cn.edu.bnuz.order_system.entity.Product;
import cn.edu.bnuz.order_system.service.OrderService;
import cn.edu.bnuz.order_system.service.impl.OrderServiceImpl;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-05-14-17:02
 */
public class ListProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int payment = Integer.parseInt(req.getParameter("payment"));;
        int id = Integer.parseInt(req.getParameter("orderId"));

        OrderService service = new OrderServiceImpl();
        List<Product> products = service.getProductsById(id);
        for (Product p:products){
            System.out.println(p);
        }
        req.setAttribute("products",products);
        req.setAttribute("payment",payment);
        req.getRequestDispatcher("orderInfo.jsp").forward(req,resp);
    }
}
