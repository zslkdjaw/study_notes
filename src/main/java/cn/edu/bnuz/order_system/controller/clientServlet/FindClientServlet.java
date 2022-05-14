package cn.edu.bnuz.order_system.controller.clientServlet;

import cn.edu.bnuz.order_system.entity.Client;
import cn.edu.bnuz.order_system.service.ClientService;
import cn.edu.bnuz.order_system.service.impl.ClientServiceImpl;
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
 * @create 2022-05-08-15:45
 */
public class FindClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取表单数据
        //客户ID
        String clientId = req.getParameter("clientId");

        int id = Integer.parseInt(clientId);
        //2 创建 ClientService对象
        ClientService clientService = new ClientServiceImpl();

        //3 调用 clientService getById方法
        Client cl = clientService.getById(id);

        List<Client> list = new ArrayList<>();
        list.add(cl);
        //4 将结果放到request域当中
        req.setAttribute("clients",list);
        // 请求重定向
        req.getRequestDispatcher("/clientList.jsp").forward(req,resp);
    }
}
