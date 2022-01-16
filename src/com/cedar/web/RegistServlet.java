package com.cedar.web;

import com.cedar.pojo.User;
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
 * @create 2021-11-27 17:00
 * @name JavaWeb
 */
public class RegistServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();

    //deprecated
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        接受参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        // 判断验证码是否正确，该过程与index中的重复

        if (code.equalsIgnoreCase("6n6np")){

            // 判断用户名是否存在
            if (userService.exitsUsername(username)){
                //用户名已存在，跳转回注册页面
                req.setAttribute("msg","用户名[" + username + "]已存在!");
                req.setAttribute("username",username);
                req.setAttribute("password",password);
                req.setAttribute("repwd",password);
                req.setAttribute("email",email);

                System.out.println("用户名[" + username + "]已存在!");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //用户名不存在，跳转到注册成功页面，完成注册
                userService.regist(new User(0, username, password, email));

                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        }else{
            System.out.println("验证码 " + code +" 错误");
            //存在缓存问题
            req.setAttribute("msg","验证码 " + code +" 错误");
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.setAttribute("repwd",password);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }

}
