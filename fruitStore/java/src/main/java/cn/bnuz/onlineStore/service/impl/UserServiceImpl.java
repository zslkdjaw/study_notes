package cn.bnuz.onlineStore.service.impl;


import cn.bnuz.onlineStore.dao.CarDao;
import cn.bnuz.onlineStore.dao.GoodsDao;
import cn.bnuz.onlineStore.dao.OrderDao;
import cn.bnuz.onlineStore.dao.UserDao;
import cn.bnuz.onlineStore.entity.Car;
import cn.bnuz.onlineStore.entity.Goods;
import cn.bnuz.onlineStore.entity.Order;
import cn.bnuz.onlineStore.entity.User;
import cn.bnuz.onlineStore.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-11-25-12:34
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    GoodsDao goodsDao;
    @Autowired
    CarDao carDao;
    @Autowired
    OrderDao orderDao;

    @Override
    public User login(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Strings.isNotEmpty(user.getUserName()),User::getUserName,user.getUserName());
        lqw.eq(Strings.isNotEmpty(user.getUserPassword()), User::getUserPassword, user.getUserPassword());
        User ans = userDao.selectOne(lqw);
        return ans;
    }
    @Override
    public Car getShopCar(Integer userId) {
        LambdaQueryWrapper<Car> lqw = new LambdaQueryWrapper<>();
        lqw.eq(true,Car::getUserId, userId);

        Car car = carDao.selectOne(lqw);
        car.setGoodsList(goodsDao.selectCarByUserId(userId));

        return car;
    }
    @Override
    public Integer getShopCarId(Integer userId) {
        LambdaQueryWrapper<Car> lqw = new LambdaQueryWrapper<>();
        lqw.eq(true,Car::getUserId,userId);
        Car car = carDao.selectOne(lqw);
        return car.getCarId();
    }

    @Override
    public Boolean isExistInShopCar(Integer goodsId,Integer userId) {
        Goods g = goodsDao.isExistCar(goodsId,userId);
        return g != null;
    }

    @Override
    public Boolean addGoodsToCar( Integer carId,Integer goodsId) {
        goodsDao.insertGoodsToCar(carId,goodsId);
        return true;
    }

    @Override
    public Boolean removeGoodsToCar(Integer carId, Integer goodsId) {
        goodsDao.deleteGoodsToCar(carId,goodsId);
        return null;
    }

    @Override
    public Integer addCarToOrder(Car car) {
        //购物车内的商品购买
        Order order = new Order(car);
        orderDao.insert(order);
        //新购物车Id由数据库的触发器进行分配

        //获取用户新购物车Id
        LambdaQueryWrapper<Car> lqw = new LambdaQueryWrapper<>();
        lqw.eq(true,Car::getUserId, car.getUserId());
        //返回给前台
        return carDao.selectOne(lqw).getCarId();
    }

    @Override
    public List<Order> getAllOrder(Integer userId) {
        LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();
        lqw.eq(true,Order::getUserId,userId);
        lqw.orderBy(true,false,Order::getOrderTime);
        return orderDao.selectList(lqw);
    }

    @Override
    public List<Goods> getGoodsByOrderId(Integer orderId) {
        return orderDao.getGoodsByOrderId(orderId);
    }

}
