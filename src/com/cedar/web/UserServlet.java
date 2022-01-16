package com.cedar.web;

import com.cedar.pojo.User;
import com.cedar.service.impl.UserService;
import com.cedar.service.impl.UserServiceImpl;
import com.cedar.utils.WebUtils;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-29 9:06
 * @name JavaWeb
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 实现登录功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("welcome to UserServlet-login()");

        //接受参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");



        //请求参数注入javaBean
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        User loginUser = userService.login(user);

        //调用service的login方法处理业务
        if (loginUser == null){
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

            //将用户信息user存入session域中，便于多个jsp文件读取user信息
            req.getSession().setAttribute("user",loginUser);

            //设置cookie存储用户名信息 在客户端可以通过cookie.获取
            Cookie cookie = new Cookie("username", username);
            //设置当前 Cookie 一分钟内有效，不设置maxAge时，默认当前会话内有效
            cookie.setMaxAge(60*60*12*7);
            resp.addCookie(cookie);

            //请求转发到login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     * 实现注册功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("welcome to UserServlet-regist()");
        //        接受参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");


        // 判断验证码是否正确，该过程与index中的重复
        String kaptchaSessionKey = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        // 删除 Session 中的验证码
        req.getSession().removeAttribute("KAPTCHA_SESSION_KEY");

        if ( kaptchaSessionKey != null && code.equalsIgnoreCase(kaptchaSessionKey)){

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
                //请求参数注入javaBean
                User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
                //用户名不存在，跳转到注册成功页面，完成注册
                userService.regist(user);

                req.getSession().setAttribute("registeUser",user);

                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);

            }
        } else{
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

    /**
     * 处理判断用户名是否存在的ajax请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("收到ajax请求");
        String username = req.getParameter("username");
        boolean exitsUsername = userService.exitsUsername(username);

        HashMap<String, Object> resultMap = new HashMap<>();

        //把处理结果，封装成map返回
        resultMap.put("existsUsername",exitsUsername);

        Gson gson = new Gson();

        String s = gson.toJson(resultMap);

        resp.getWriter().write(s);

    }
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 1、销毁 Session 中用户登录的信息（或者销毁 Session)
        req.getSession().removeAttribute("user");
//        req.getSession().invalidate();

        // 2、重定向到首页（或登录页面）。
//        resp.sendRedirect(req.getContextPath());
        resp.sendRedirect(req.getContextPath()+"/pages/user/login.jsp");

    }
}
