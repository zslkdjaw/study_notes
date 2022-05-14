package cn.edu.bnuz.order_system.service;

import cn.edu.bnuz.order_system.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-05-12-12:37
 */

public interface ProductService {
    /**
     * function annotation:通过id查询指定Client
     * TODO
     * @param id
     * @return Client
     * @throws SQLException,ClassNotFoundException
     */
    Product getById(Integer id) throws SQLException, ClassNotFoundException;

    /**
     * function annotation:通过非ID进行模糊查询
     * TODO
     * @return
     * @param product
     */
    List<Product> getByManyCondition(Product product) throws SQLException, ClassNotFoundException;

    /**
     * function annotation:查询所有Client
     * TODO
     * @return List<product>
     * @throws   SQLException,ClassNotFoundException
     */
    List<Product> getAll() throws SQLException, ClassNotFoundException;

    /**
     * function annotation:增加一个product
     * TODO
     * @param product
     * @return Boolean
     * @exception  SQLException,ClassNotFoundException
     */
    Boolean addProduct(Product product) throws SQLException, ClassNotFoundException;

    /**
     * function annotation:删除指定Client
     * TODO
     * @param product
     * @return Boolean
     * @exception SQLException,ClassNotFoundException
     */
    Boolean removeProduct(Product product) throws SQLException, ClassNotFoundException;

    /**
     * function annotation:修改client
     * TODO
     * @param product
     * @return Boolean
     * @exception SQLException,ClassNotFoundException
     */
    Boolean updateProduct(Product product) throws SQLException, ClassNotFoundException;

}
