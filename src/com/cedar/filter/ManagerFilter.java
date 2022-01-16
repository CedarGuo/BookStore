package com.cedar.filter;

import com.cedar.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-12-06 17:46
 * @name JavaWeb
 */
public class ManagerFilter implements Filter {
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //在web工程启动时执行初始化
        System.out.println("ManagerFilter初始化成功！");
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //解决响应乱码问题
        servletResponse.setContentType("text/html;charset=UTF-8");
        //1.从httpsession域中获得user信息
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null){
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        }else {
              if (user.getUsername().equals(filterConfig.getInitParameter("adminUsername")) ){

                  filterChain.doFilter(servletRequest,servletResponse);
              }else {
                  httpServletResponse.getWriter().write("当前用户无管理员权限！");
              }
        }


    }

    @Override
    public void destroy() {
        //web工程停止时执行
        System.out.println("ManagerFilter销毁成功！");
        this.filterConfig = null;
    }

    public void sendSometing(String hint , ServletResponse servletResponse) throws IOException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().write(hint);
    }

}
