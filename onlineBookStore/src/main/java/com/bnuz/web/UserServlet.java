package com.bnuz.web;

import com.bnuz.pojo.User;
import com.bnuz.service.UserService;
import com.bnuz.service.impl.UserServiceImpl;
import com.bnuz.utils.WebUtils;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet("/userservlet")
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("正在处理登录业务！");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loginUser = userService.login(new User(null, username, password, null));
        if (loginUser == null) {
            req.setAttribute("msg", "输入的用户名或密码有误！");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            Cookie cookie= new Cookie("user",username+":"+password);
            cookie.setMaxAge(60*60*24*7);
            cookie.setPath(req.getContextPath());
            resp.addCookie(cookie);
            System.out.println(cookie.getValue());
            req.getSession().setAttribute("user",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
    /**
     * 登出/账户注销功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1 销毁Session/清楚Session域中的用户信息
        req.getSession().invalidate();
        // 2 请求重定向
        resp.sendRedirect(req.getContextPath());
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("正在处理注册业务！");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
//        System.out.println("username=" + username);
//        System.out.println("password=" + password);
//        System.out.println("email=" + email);
//        System.out.println("验证码 code=" + code);
        User user=WebUtils.copyParamToBean(req.getParameterMap(),new User());

        if (token!=null && token.equalsIgnoreCase(code)) {
            if (userService.existsUsername(username)) {
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                System.out.println("用户名【" + username + "】已存在！");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                userService.registUser(new User(null, username, password, email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码【" + code + "】错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }


}

