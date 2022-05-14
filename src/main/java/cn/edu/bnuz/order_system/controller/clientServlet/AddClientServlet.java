package cn.edu.bnuz.order_system.controller.clientServlet;

import cn.edu.bnuz.order_system.entity.Client;
import cn.edu.bnuz.order_system.service.ClientService;
import cn.edu.bnuz.order_system.service.impl.ClientServiceImpl;
import cn.edu.bnuz.order_system.utils.DateCompareUtils;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-05-08-17:38
 */
public class AddClientServlet extends HttpServlet {
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

        //客户名字
        String clientName = req.getParameter("clientName");
        //客户性别
        String sex = new String(req.getParameter("sex").getBytes("ISO-8859-1"),"utf-8");
        //客户年龄
        //客户生日
        String birth = req.getParameter("birth");

        //2 创建 ClientService对象
        ClientService clientService = new ClientServiceImpl();

        //3 调用 clientService addClient方法
        clientService.addClient(new Client(id,clientName,sex,0),birth);
        /**
         * function annotation:
         * 此处是再次调出所有Client，将数据重定向导入到JSP中
         *
         * TODO
         * 但是还有一种方法直接重定向ListClientServlet就可以直接，不知道怎么做
         * 已经实现
         */
//        List<Client> clients = clientService.getAll();
//        // 3 将查询出的所有Client放进request域当中
//        req.setAttribute("clients",clients);
//
//        req.getRequestDispatcher("/clientList.jsp").forward(req,resp);

        resp.sendRedirect(req.getContextPath()+"/listClient");
    }
}
