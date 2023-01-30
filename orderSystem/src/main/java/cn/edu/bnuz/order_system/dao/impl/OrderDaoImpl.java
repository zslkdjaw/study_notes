package cn.edu.bnuz.order_system.dao.impl;

import cn.edu.bnuz.order_system.dao.OrderDao;
import cn.edu.bnuz.order_system.entity.Order;
import cn.edu.bnuz.order_system.entity.Product;
import cn.edu.bnuz.order_system.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-05-14-15:47
 */
public class OrderDaoImpl implements OrderDao {
    @Override
    public List<Order> selectAll() throws SQLException, ClassNotFoundException {
        List<Order> orders = new ArrayList<>();
        String sql = "select client_TB.clientName,order_TB.clientId,order_TB.orderId,order_TB.orderDate,order_TB.papyment\n" +
                     "from order_TB , orderproduct,client_TB,product \n" +
                     "where order_TB.orderId = orderproduct.orderId and order_TB.clientId = client_TB.clientId and\n" +
                      "orderproduct.productId = product.productId\n" +
                     "group by client_TB.clientName,order_TB.clientId,order_TB.orderId,order_TB.orderDate,order_TB.papyment;";
        //数据库连接资源
        Connection connection = JdbcUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            Order order = new Order();
            order.setClientName(resultSet.getString(1));
            order.setClientId(resultSet.getInt(2));
            order.setOrderId(resultSet.getInt(3));
            order.setOrderDate(resultSet.getDate(4));
            order.setPayment(resultSet.getInt(5));
            orders.add(order);
            //添加
        }
        //数据库释放资源
        JdbcUtils.release(resultSet,connection,statement);
        return orders;
    }

    @Override
    public List<Product> selectProductsById(Integer id) throws SQLException, ClassNotFoundException {

        String sql = "select product.productName ,product.productId,orderproduct.quantity , price\n" +
                     "from orderproduct , product,order_TB\n" +
                     "where orderproduct.productId = product.productId and order_TB.orderId = orderproduct.orderId and\n" +
                     "order_TB.orderId = ?;";
        //连接数据库
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet resultSet = ps.executeQuery();
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setProductName(resultSet.getString(1));
            product.setProductId(resultSet.getInt(2));
            product.setQuantity(resultSet.getInt(3));
            product.setPrice(resultSet.getFloat(4));
            products.add(product);
        }
        JdbcUtils.release(resultSet,connection,ps);
        return products;
    }


}
