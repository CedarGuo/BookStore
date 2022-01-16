package com.cedar.dao.impl;

import com.cedar.pojo.User;

import java.sql.Connection;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-26 11:14
 * @name JavaWeb
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 如果返回 null,说明没有这个用户.
     */
    public User queryUserByUsername(Connection conn, String username);

    /**
     * 根据 用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回 null,说明用户名或密码错误
     */
    public User queryUserByUsernameAndPassword(Connection conn,String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return 返回0 表示操作失败，其他是 sql 语句影响的行数
     */
    public int saveUser(Connection conn, User user);

}
