package com.cedar.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-11-26 10:44
 * @name JavaWeb
 */
public abstract class BaseDao<T> {

    Class<T> clazz = null;
    //在定义子类时，声明父类的泛型
    //在创建子类对象时，希望先在父类中 获得 子类所继承的父类的泛型类型，再加载父类的查询方法。
    //因此可以将此过程放在父类的空参构造器中，或者代码块内
    {
        //this代表子类对象
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType type = (ParameterizedType) genericSuperclass;

        //Type类型是所有类型的父接口
        Type[] actualTypeArguments = type.getActualTypeArguments();

        clazz = (Class<T>) actualTypeArguments[0];
    }

    /**
     *通用增删改操作
     * @param conn
     * @param sql
     * @param args
     * @return 修改影响的行数，失败返回0.
     */
    public int update(Connection conn, String sql, Object ...args){
        QueryRunner queryRunner = new QueryRunner();
        int updateColumnCount = 0;
        try {
            updateColumnCount = queryRunner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return updateColumnCount;
    }


    /**
     * 通用查询操作，查询单列
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    public T queryForOne(Connection conn,String sql,Object ...args) {
        QueryRunner queryRunner = new QueryRunner();
        BeanHandler<T> handler = new BeanHandler<>(clazz);
//        System.out.println(clazz);

        T query = null;
        //queryRunner的query方法内关闭了 ps 和 rs，无需重复关闭资源
        try {
            query = queryRunner.query(conn, sql, handler, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return query;
    }

    /**
     * 通用查询操作，查询对象列表
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    public List<T> queryForList(Connection conn, String sql, Object ...args){
        QueryRunner queryRunner = new QueryRunner();
        BeanListHandler<T> listHandler = new BeanListHandler<>(clazz);

        //queryRunner的query方法内关闭了 ps 和 rs，无需重复关闭资源
        List<T> query = null;
        try {
            query = queryRunner.query(conn, sql, listHandler, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return query;
    }

    /**
     * 查询数值
     * @param conn
     * @param sql
     * @param args
     * @return
     */
    public <E> E getForSingleValue(Connection conn,String sql,Object ...args)  {
        QueryRunner queryRunner = new QueryRunner();
        ScalarHandler handler = new ScalarHandler();

        E value = null;
        try {
            value = (E) queryRunner.query(conn, sql, handler, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return value;
    }
}
