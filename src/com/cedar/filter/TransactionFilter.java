package com.cedar.filter;

import com.cedar.dao.impl.BaseDao;
import com.cedar.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author Cedar
 * @version {}
 * @description 以线程为单位，实现事务管理
 * @create 2021-12-06 23:16
 * @name JavaWeb
 */
public class TransactionFilter implements Filter {

    private  FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        System.out.println("TransactionFilter初始化成功！");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();
        }catch (Exception e){
            JDBCUtils.rollbackAndClose();
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
        System.out.println("TransactionFilter已成功销毁！");
    }
}
