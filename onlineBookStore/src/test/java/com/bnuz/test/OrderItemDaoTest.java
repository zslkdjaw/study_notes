package com.bnuz.test;

import com.bnuz.dao.OrderItemDao;
import com.bnuz.dao.impl.OrderItemDaoImpl;
import com.bnuz.pojo.OrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author Zhang Hao
 * @create 2022-12-19-23:22
 */
public class OrderItemDaoTest {
    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"java 从入门到精通", 1,new BigDecimal(100),new
                BigDecimal(100),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript 从入门到精通", 2,new
                BigDecimal(100),new BigDecimal(200),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Netty 入门", 1,new BigDecimal(100),new
                BigDecimal(100),"1234567890"));
    }
}
