package cn.edu.bnuz.order_system.dao.impl;

import cn.edu.bnuz.order_system.dao.ProductDao;
import cn.edu.bnuz.order_system.entity.Client;
import cn.edu.bnuz.order_system.entity.Product;
import cn.edu.bnuz.order_system.utils.DateCompareUtils;
import cn.edu.bnuz.order_system.utils.JdbcUtils;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-05-12-10:56
 */
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product selectById(Integer id) throws SQLException, ClassNotFoundException {
        String sql = "select * from product where productId = ?";
        //连接数据库
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet resultSet = ps.executeQuery();
        Product product = new Product();
        while (resultSet.next()) {
            product.setProductId(resultSet.getInt(1));
            product.setProductName(resultSet.getString(2));
            product.setPrice(resultSet.getFloat(3));
            product.setStock(resultSet.getInt(4));
        }
        JdbcUtils.release(resultSet,connection,ps);

        return product;
    }

    @Override
    public List<Product> selectByManyCondition(Product product) throws SQLException, ClassNotFoundException {
        //动态拼接
        String[] patch = {"productName like ","price like ","stock like "};
        String pat = "";
        if(product.getProductName()!=null&& !product.getProductName().equals("")){
            pat += "and "+patch[0]+"'%"+product.getProductName()+"%'";
        }
        if (product.getPrice()!= 0){
            pat += "and "+patch[1]+"'%"+(int)product.getPrice()+"%'";;
        }
        if (product.getStock()!= 0){
            pat += "and "+patch[2]+"'%"+product.getStock()+"%'";;
        }
        pat = pat.substring("and ".length(),pat.length());

        ArrayList<Product> list = new ArrayList<>();

        String sql = "select * from product where "+pat;
        System.out.println(sql);

        Connection connection = JdbcUtils.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            Product product1 = new Product();
            product1.setProductId(resultSet.getInt(1));
            product1.setProductName(resultSet.getString(2));
            product1.setPrice(resultSet.getFloat(3));
            product1.setStock(resultSet.getInt(4));

            list.add(product1);
        }
        //数据库释放资源
        JdbcUtils.release(resultSet,connection,statement);
        return list;

    }

    @Override
    public List<Product> selectAll() throws SQLException, ClassNotFoundException {
        List<Product> products = new ArrayList<>();

        String sql = "select * from product";
        //数据库连接资源
        Connection connection = JdbcUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            Product product = new Product();

            product.setProductId(resultSet.getInt(1));
            product.setProductName(resultSet.getString(2));
            product.setPrice(resultSet.getFloat(3));
            product.setStock(resultSet.getInt(4));
            //添加
            products.add(product);
        }
        //数据库释放资源
        JdbcUtils.release(resultSet,connection,statement);
        return products;
    }

    @Override
    public Boolean insert(Product product) throws SQLException, ClassNotFoundException {
        String sql = "insert into product value(?,?,?,?)";

        Connection connection = JdbcUtils.getConnection();

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1,product.getProductId());
        ps.setString(2,product.getProductName());
        ps.setFloat(3,product.getPrice());
        ps.setInt(4,product.getStock());
        //错误抓取
        try {
            ps.execute();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            JdbcUtils.release(connection,ps);
        }
        return true;
    }

    @Override
    public Boolean deleteById(Product product) throws SQLException, ClassNotFoundException {
        String sql = "delete from product where productId= ?";

        Connection connection = JdbcUtils.getConnection();

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1,product.getProductId());

        //错误抓取
        try {
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            JdbcUtils.release(connection,ps);
        }
        JdbcUtils.release(connection,ps);

        return true;
    }
    @Override
    public Boolean updateById(Product product) throws SQLException, ClassNotFoundException {
        //动态拼接
        String[] patch = {"productName=","price=","stock="};
        String pat = "";
        if(product.getProductName()!=null&& !product.getProductName().equals("")){
            pat += patch[0]+"'"+product.getProductName()+"',";
        }
        if (product.getPrice()!=-1){
            pat += patch[1]+"'"+product.getPrice()+"',";
        }
        if (product.getStock() != -1 ){
            pat += patch[2]+"'"+product.getStock()+"',";
        }
        //去掉最后一个 , 字符
        pat = pat.substring(0,pat.length()-1);

        String sql  = " update product set "+pat+"where productId='"+product.getProductId()+"'" ;
        //System.out.println(sql);
        Connection connection = JdbcUtils.getConnection();
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate(sql);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            JdbcUtils.release(connection,statement);
        }
        return true;
    }

}
