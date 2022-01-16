package com.cedar.test;

import com.cedar.pojo.User;
import com.cedar.service.impl.UserService;
import com.cedar.service.impl.UserServiceImpl;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-27 15:47
 * @name JavaWeb
 */

public class UserServiceImplTest {

    private UserService userService = new UserServiceImpl();
    /**
     * 注册用户
     * @return 注册成功返回1，注册失败返回0.
     */
    @Test
    public void registe() {
        int registe = userService.regist(new User(0, "test2", "test2", "test2@cedar.com"));
        System.out.println(registe);
        if (registe == 1){
            System.out.println("注册成功");
        }else {
            System.out.println("注册失败");
        }
    }

    /**
     * 用户登录
     * @return 登录成功则返回User,不成功返回null.
     */
    @Test
    public void login() {
        User login = userService.login(new User(0, "admin1", "admin", null));

        System.out.println(login);

        if (login != null){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }


    /**
     * 判断用户名是否已存在
     * @return 存在返回true,不存在返回false.
     */
    @Test
    public void exitsUsername() {
        boolean exitsUsername = userService.exitsUsername("test3");

        System.out.println(exitsUsername);

        if (exitsUsername){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名不存在");
        }
    }
}
