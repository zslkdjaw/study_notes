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
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-05-08-18:52
 */
public class RemoveClientServlet extends HttpServlet {
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

        //3 调用 clientService 方法
        clientService.removeClient(new Client(id));
        /**
         * function annotation:
         * 此处是再次调出所有Client，将数据重定向导入到JSP中
         *
         * TODO
         * 但是还有一种方法直接重定向ListClientServlet就可以直接，不知道怎么做
         */
        resp.sendRedirect(req.getContextPath()+"/listClient");

    }
}
