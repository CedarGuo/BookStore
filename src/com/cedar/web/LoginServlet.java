package com.cedar.web;

import com.cedar.pojo.User;
import com.cedar.service.impl.UserService;
import com.cedar.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Cedar
 * @version {deprecated version,recommand to use UserServlet}
 * @description
 * @create 2021-11-28 15:13
 * @name JavaWeb
 */
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("welcome to loginServlet");
        //接受参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //调用service的login方法处理业务
        if (userService.login(new User(0,username,password,null) ) == null){
            //方法返回null，说明查不到此用户，用户名或密码错误，跳转回login.html
            System.out.println("用户名或密码不正确，请重新登陆");
            //向request域中传递数据，用于信息回显
            req.setAttribute("errorMsg","用户名或密码不正确，请重新登陆");
            req.setAttribute("username",username);
            req.setAttribute("password",password);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            //方法返回不是null，跳转到login_success.html
            System.out.println("登录成功");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
}
