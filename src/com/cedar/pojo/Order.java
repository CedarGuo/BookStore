package com.cedar.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-12-03 22:09
 * @name JavaWeb
 */
public class Order {
    //orderId在数据库中为主键，不可重复
    private String orderId;
    //java中的时间类与数据库的时间类对应关系：
    // 数据库时间类date<->java.sql.date;数据库time<->java.sql.time;数据库TIMESTAMP/datetime<->java.sql.timestamp
    //注：要了解java.util时间类与java.sql时间类的转换方法（因为java.sql的时间类才能对应数据库的时间类，存入数据库）：
    // 通过Long类型的时间戳进行转换：java.date.getTime -> 时间戳 -> new sql.date(时间戳)

    //数据库的表t_order中，将createTime定义成了datatime类型，对应Java 中的java.sql.TimeStamp
    private Timestamp createTime;

    //订单总金额
    private BigDecimal price;

    // 0 未发货，1 已发货，2 表示已签收
    private Integer status = 0;
    private Integer userId;

    public Order() {

    }

    public Order(String orderId, Timestamp createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
