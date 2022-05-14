package cn.edu.bnuz.order_system.service.impl;

import cn.edu.bnuz.order_system.dao.ProductDao;
import cn.edu.bnuz.order_system.dao.impl.ProductDaoImpl;
import cn.edu.bnuz.order_system.entity.Product;
import cn.edu.bnuz.order_system.service.ProductService;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-05-12-12:39
 */
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();
    @Override
    public Product getById(Integer id) throws SQLException, ClassNotFoundException {
        return productDao.selectById(id);
    }

    @Override
    public List<Product> getByManyCondition(Product product) throws SQLException, ClassNotFoundException {
        return productDao.selectByManyCondition(product);
    }

    @Override
    public List<Product> getAll() throws SQLException, ClassNotFoundException {
        return productDao.selectAll();
    }

    @Override
    public Boolean addProduct(Product product) throws SQLException, ClassNotFoundException {
        return productDao.insert(product);
    }

    @Override
    public Boolean removeProduct(Product product) throws SQLException, ClassNotFoundException {
        return productDao.deleteById(product);
    }

    @Override
    public Boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
        return productDao.updateById(product);
    }
}
