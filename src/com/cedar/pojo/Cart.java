package com.cedar.pojo;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-12-02 20:00
 * @name JavaWeb
 */
public class Cart {
    private Integer totalCount;
    private BigDecimal totalPrice;
    //Map的key是CartItem的id,即商品的id；value是cartItem
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    public Cart() {
    }

    public Cart(Integer totalCount, BigDecimal totalPrice, Map<Integer, CartItem> items) {
        this.totalCount = totalCount;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    /**
     * 计算当前购物车商品的总数量
     * @return
     */
    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer,CartItem> entry : items.entrySet())
        { totalCount += entry.getValue().getCount(); }
        return totalCount;
    }

    /**
     * 计算当前购物车商品的总金额
     * @return
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry:items.entrySet()
             ) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    /**
     * 向购物车cart中添加商品cartItem
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());
        //将图书id作为map的key,将cartItem作为map的value
        if (item == null){
            items.put(cartItem.getId(),cartItem);
        }else {
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 根据商品id，删除购物车中的商品
     * @param id
     */
    public void deleteItemById(int id){
        items.remove(id);
    }

    /**
     * 修改购物车中，指定id商品的数量
     * @param id
     * @param count
     */
    public void modifyItemCount(int id, int count){
        CartItem cartItem = items.get(id);

        if (cartItem != null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public void clearCart(){
        items.clear();
    }
    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + totalCount +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                '}';
    }
}
