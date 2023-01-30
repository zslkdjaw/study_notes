package cn.edu.bnuz.order_system.service.impl;

import cn.edu.bnuz.order_system.dao.OrderDao;
import cn.edu.bnuz.order_system.dao.impl.OrderDaoImpl;
import cn.edu.bnuz.order_system.entity.Order;
import cn.edu.bnuz.order_system.entity.Product;
import cn.edu.bnuz.order_system.service.OrderService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-05-14-16:39
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        return orderDao.selectAll();
    }

    @Override
    public List<Product> getProductsById(Integer id) throws SQLException, ClassNotFoundException {
        return orderDao.selectProductsById(id);
    }
}
