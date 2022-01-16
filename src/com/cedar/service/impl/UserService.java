package com.cedar.service.impl;

import com.cedar.pojo.User;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-27 15:22
 * @name JavaWeb
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return 注册成功返回1，注册失败返回0.
     */
    public int regist(User user);

    /**
     * 用户登录
     * @param user
     * @return 登录成功则返回User,不成功返回null.
     */
    public User login(User user);

    /**
     * 判断用户名是否已存在
     * @param username
     * @return 存在返回true,不存在返回false.
     */
    public boolean exitsUsername(String username);


}
