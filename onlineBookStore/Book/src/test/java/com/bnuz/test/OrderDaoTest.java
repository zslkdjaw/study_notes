package com.bnuz.test;

import com.bnuz.dao.OrderDao;
import com.bnuz.dao.impl.OrderDaoImpl;
import com.bnuz.pojo.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Zhang Hao
 * @create 2022-12-19-23:21
 */
public class OrderDaoTest {
    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234567891",new Date(),new BigDecimal(100),0, 1));
    }
}

