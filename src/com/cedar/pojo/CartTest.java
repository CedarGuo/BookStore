package com.cedar.pojo;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import static org.junit.Assert.*;

/**
 * @author Cedar
 * @version {}
 * @description
 * @create 2021-12-03 17:29
 * @name JavaWeb
 */
public class CartTest {


    @Test
    public void deleteItemById() {
        CartItem gh = new CartItem(1, "gh", 1, new BigDecimal(30), new BigDecimal(30));
        CartItem yu = new CartItem(2, "yu", 1, new BigDecimal(40), new BigDecimal(40));
        CartItem vbnm = new CartItem(3, "vbnm", 1, new BigDecimal(50), new BigDecimal(50));

        LinkedHashMap<Integer, CartItem> items = new LinkedHashMap<>();
        items.put(gh.getId(),gh);
        items.put(yu.getId(),yu);
        items.put(vbnm.getId(),vbnm);

        Cart cart = new Cart(null,null,items);
        System.out.println(cart);

        cart.deleteItemById(1);
        System.out.println(cart);

    }

    @Test
    public void modifyItemCount(){
        CartItem gh = new CartItem(1, "gh", 1, new BigDecimal(30), new BigDecimal(30));
        CartItem yu = new CartItem(2, "yu", 1, new BigDecimal(40), new BigDecimal(40));
        CartItem vbnm = new CartItem(3, "vbnm", 1, new BigDecimal(50), new BigDecimal(50));

        LinkedHashMap<Integer, CartItem> items = new LinkedHashMap<>();
        items.put(gh.getId(),gh);
        items.put(yu.getId(),yu);
        items.put(vbnm.getId(),vbnm);

        Cart cart = new Cart(null,null,items);

        System.out.println(cart);

        cart.modifyItemCount(1,3);
        System.out.println(cart);
    }
}