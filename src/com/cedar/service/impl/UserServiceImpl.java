package com.cedar.service.impl;

import com.cedar.dao.impl.UserDao;
import com.cedar.dao.impl.UserDaoImpl;
import com.cedar.pojo.User;
import com.cedar.utils.JDBCUtils;

import java.sql.Connection;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-27 15:31
 * @name JavaWeb
 */
public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    /**
     * 注册用户
     * @param user
     * @return 注册成功返回1，注册失败返回0.
     */
    @Override
    public int regist(User user) {
        Connection conn = JDBCUtils.getConnection();

        int update = dao.saveUser(conn, user);


        return update;
    }

    /**
     * 用户登录
     * @param user 实际是user中的username和password.
     * @return 登录成功则返回User,不成功返回null.
     */
    @Override
    public User login(User user) {
        Connection conn = JDBCUtils.getConnection();

        User user1 = dao.queryUserByUsernameAndPassword(conn, user.getUsername(), user.getPassword());

        return user1;
    }

    /**
     * 判断用户名是否已存在
     * @param username
     * @return 存在返回true,不存在返回false.
     */
    @Override
    public boolean exitsUsername(String username) {

        Connection conn = JDBCUtils.getConnection();

        User user = dao.queryUserByUsername(conn, username);


        if (user != null){
            return true;
        }else {

            return false;
        }

    }
}
