package com.cedar.test;

import com.cedar.dao.impl.BaseDao;
import com.cedar.dao.impl.UserDaoImpl;
import com.cedar.pojo.User;
import com.cedar.utils.JDBCUtils;

import org.junit.Test;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-26 11:15
 * @name JavaWeb
 */
public class BaseDaoTest {

    private BaseDao dao = new UserDaoImpl();

    private Connection conn = JDBCUtils.getConnection();
    @Test
    public void dateTest(){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
    }

    @Test
    public void update() {

    }

    @Test
    public void queryForOne() {
        String sql = "select * from t_user where username=?";
        User one = (User) dao.queryForOne(conn, sql, "admin");
        System.out.println(one);
        JDBCUtils.closeConnection(conn);
    }

    @Test
    public void queryForList() {
    }

    @Test
    public void getForSingleValue() {
        String sql = "select count(*) from t_user";
        Object value = dao.getForSingleValue(conn, sql);

        System.out.println(value);
        JDBCUtils.closeConnection(conn);
    }
}
