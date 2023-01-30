package com.bnuz.service;

import com.bnuz.pojo.Cart;

/**
 * @author Zhang Hao
 * @create 2022-12-19-23:28
 */

public interface OrderService {
    String createOrder(Cart cart, Integer userId);
}
