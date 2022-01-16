package com.cedar.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Cedar
 * @version {}
 * @description 定义为抽象类，只允许继承，不允许创建实例对象
 * @create 2021-11-29 11:05
 * @name JavaWeb
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置服务器字符集，解决请求乱码问题
        req.setCharacterEncoding("UTF-8");
        //设置服务器和浏览器字符集，解决响应乱码问题
        resp.setContentType("text/html;charset=UTF-8");

        //获取request请求的action参数，代表方法名
        String method = req.getParameter("action");

        try {
            Method declaredMethod = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);

            declaredMethod.setAccessible(true);

            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            // 把异常抛给 Filter 过滤器
            throw new RuntimeException();
        }
    }
}
