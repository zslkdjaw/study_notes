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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-05-12-13:02
 */
public class FindProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService service = new ProductServiceImpl();
        List<Product> products = new ArrayList<>();
        //1 获取表单数据
        String productId = req.getParameter("productId");

        //是否模糊查询
        //id=“” 模糊查询
        if (productId.equals("")){
            //数据转化
            String productName = new String(req.getParameter("productName").getBytes("ISO-8859-1"),"utf-8");;

            String price = req.getParameter("price");
            int pr = price.equals("")? 0: Integer.parseInt(price);

            String stock = req.getParameter("stock");
            int st = stock.equals("")? 0:Integer.parseInt(stock);

             products = service.getByManyCondition(new Product(0,productName,pr,st));
            // 3 将查询出的所有Client放进request域当中
            req.setAttribute("products",products);
            // 4 将请求转发到index.jsp
            req.getRequestDispatcher("/productList.jsp").forward(req,resp);

        }
        //精确查询
        else{
            int id = Integer.parseInt(productId);

            products.add(service.getById(id));
            req.setAttribute("products",products);
            req.getRequestDispatcher("/productList.jsp").forward(req,resp);
        }

    }
}
