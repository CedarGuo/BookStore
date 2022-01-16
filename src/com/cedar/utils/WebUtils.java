package com.cedar.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-29 11:50
 * @name JavaWeb
 */
public class WebUtils {
    /**
     * 参数注入
     * @param value Map类型，如果是request域的数据注入，可以传入httpRequestMap
     * @param bean 空的javaBean对象
     * @param <T>
     * @return 返回注入参数的javaBean对象
     */
    public static <T> T copyParamToBean(Map value , T bean ){
        try {
            System.out.println("注入之前：" + bean);
            /*** 把所有请求的参数都注入到 user 对象中 */

            BeanUtils.populate(bean, value);

            System.out.println("注入之后：" + bean);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }

    /**
     * 将字符串转换成为int类型的数据，无法转换时赋予默认值（分页功能用到了默认值）
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue ){
        try {
            return Integer.parseInt(strInt);
        }
        catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
