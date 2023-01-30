package cn.edu.bnuz.order_system.service;

import cn.edu.bnuz.order_system.entity.Order;
import cn.edu.bnuz.order_system.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-05-14-16:38
 */

public interface OrderService {
    /**
     * function annotation:返回所有订单
     * @return List<Order>
     */
    List<Order> getAll() throws SQLException, ClassNotFoundException;

    /**
     * function annotation: 返回订单商品
     * @return List<Product>
     */
    List<Product> getProductsById(Integer id) throws SQLException, ClassNotFoundException;
}
