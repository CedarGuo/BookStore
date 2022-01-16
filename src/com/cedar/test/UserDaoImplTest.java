package com.cedar.test;

import com.cedar.dao.impl.UserDaoImpl;
import com.cedar.pojo.User;
import com.cedar.utils.JDBCUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-26 17:23
 * @name JavaWeb
 */
public class UserDaoImplTest {

    private Connection conn = JDBCUtils.getConnection();
    private UserDaoImpl dao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        User admin = dao.queryUserByUsername(conn, "test1");
        if (admin == null){
            System.out.println("查不到admin");
        }else {
            System.out.println(admin);
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        User user = dao.queryUserByUsernameAndPassword(conn, "admin1", "admin");
        if (user == null){
            System.out.println("用户名 or 密码错误");
        }else{
            System.out.println(user);
        }
    }

    @Test
    public void saveUser() {
        User user = new User(0,"test1","test1","test1@cedar.com");
        int i = dao.saveUser(conn, user);
        System.out.println(i);
        if (i == -1){
            System.out.println("插入失败！");
        }else {
            System.out.println("插入了 " + i + " 行");
        }

    }
}
