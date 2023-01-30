package cn.edu.bnuz.order_system.dao;

import cn.edu.bnuz.order_system.entity.Order;
import cn.edu.bnuz.order_system.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-05-12-14:20
 */

public interface OrderDao {
    /**
     * function annotation:返回所有订单
     * @return List<Order>
     */
    List<Order> selectAll() throws SQLException, ClassNotFoundException;

    /**
     * function annotation: 返回订单商品
     * @return List<Product>
     */
    List<Product> selectProductsById(Integer id) throws SQLException, ClassNotFoundException;
}
