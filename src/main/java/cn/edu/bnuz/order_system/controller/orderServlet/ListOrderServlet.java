package cn.edu.bnuz.order_system.controller.orderServlet;

import cn.edu.bnuz.order_system.entity.Order;
import cn.edu.bnuz.order_system.entity.Product;
import cn.edu.bnuz.order_system.service.OrderService;
import cn.edu.bnuz.order_system.service.ProductService;
import cn.edu.bnuz.order_system.service.impl.OrderServiceImpl;
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
 * @create 2022-05-14-16:42
 */
public class ListOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 创建service层对象
        OrderService service = new OrderServiceImpl();
        // 2 调用service层 getAll方法
        List<Order> orders = service.getAll();
        // 3 将查询出的所有Client放进request域当中
        req.setAttribute("orders",orders);

        // 4 将请求转发到index.jsp
        req.getRequestDispatcher("/orderList.jsp").forward(req,resp);
    }
}
