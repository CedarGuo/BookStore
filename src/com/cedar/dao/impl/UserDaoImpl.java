package com.cedar.dao.impl;

import com.cedar.pojo.User;
import com.cedar.utils.JDBCUtils;

import java.sql.Connection;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-26 11:19
 * @name JavaWeb
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 如果返回 null,说明没有这个用户.
     */
    @Override
    public User queryUserByUsername(Connection conn,String username) {

        String sql = "select * from t_user where username = ?";

        User user = queryForOne(conn, sql, username);

        return user;
    }

    /**
     * 根据 用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回 null,说明用户名或密码错误
     */
    @Override
    public User queryUserByUsernameAndPassword(Connection conn,String username, String password) {

        String sql = "select * from t_user where username = ? and password = ?";

        User user = queryForOne(conn, sql, username, password);

        return user;
    }

    /**
     * 用于保存用户
     * @param conn
     * @param user
     * @return 返回update操作影响的行数，保存成功返回1，保存失败返回0
     */
    @Override
    public int saveUser(Connection conn, User user) {

        String sql = "insert into t_user(username,password,email) values(?,?,?)";

        int update = update(conn, sql, user.getUsername(), user.getPassword(), user.getEmail());
        return update;
    }
}
