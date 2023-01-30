package cn.bnuz.onlineStore.dao;

import cn.bnuz.onlineStore.entity.Car;
import cn.bnuz.onlineStore.entity.Goods;
import cn.bnuz.onlineStore.entity.Order;
import cn.bnuz.onlineStore.entity.User;
import cn.bnuz.onlineStore.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-11-23-16:04
 */
@SpringBootTest
public class MyTest {
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    OrderDao orderDao;
    @Test
    public void test(){
        List<Goods> list  = goodsDao.selectList(null);
        for (Goods i : list){
            System.out.println(i.toString());
        }
    }
    @Test
    public void test2(){
        List<User> list = userDao.selectList(null);
        for (User i : list){
            System.out.println(i.toString());
        }
    }
    @Test
    public void test3(){
        User user = new User();
        user.setUserName("admi");
        user.setUserPassword("admin");
        System.out.println(userService.login(user));
    }
    @Test
    public void test4(){
        List<Goods> list = orderDao.getGoodsByOrderId(1);
        for (Goods i : list){
            System.out.println(i.toString());
        }
    }
    @Test
    public void test5(){
        Goods g = goodsDao.isExistCar(202201,10);
        System.out.println(g.toString());
    }
    @Test
    public void test6(){

        System.out.println(userService.addGoodsToCar(101,202201));
    }
    @Test
    public void test7(){
        System.out.println(goodsDao.deleteGoodsToCar(1,101));
    }
    @Test
    public void test8(){
        List<Order> list = userService.getAllOrder(202201);
        for (Order i : list){
            System.out.println(i.toString());
        }
//        Car car = new Car(2,202201,10.00);
//        userService.addCarToOrder(car);
    }

}
