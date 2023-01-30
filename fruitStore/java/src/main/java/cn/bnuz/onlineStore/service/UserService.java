package cn.bnuz.onlineStore.service;

import cn.bnuz.onlineStore.entity.Car;
import cn.bnuz.onlineStore.entity.Goods;
import cn.bnuz.onlineStore.entity.Order;
import cn.bnuz.onlineStore.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-11-25-12:33
 */

public interface UserService extends IService<User> {
    /**
     * description:
     * 根据账号密码进行登录验证
     */
    User login(User user);
    /**
     * description:
     * 获取用户购物车
     */

    Car getShopCar(Integer id);
    /**
     * description:
     * 获取用户购物车的ID
     */

    Integer getShopCarId(Integer id);
    /**
     * description:
     * 该商品是否存在于用户购物车
     */

    Boolean isExistInShopCar(Integer goodsId , Integer userId);
    /**
     * description:
     * 添加商品到购物车
     */

    Boolean addGoodsToCar(Integer carId, Integer goodsId);
    /**
     * description:
     * 移除商品到购物车
     */

    Boolean removeGoodsToCar(Integer carId, Integer goodsId);
    /**
     * description:
     * 添加购物车到订单
     */

    Integer addCarToOrder(Car car);

    /**
     * description:
     * 获取所有订单
     */
    List<Order> getAllOrder(Integer userId);

    List<Goods> getGoodsByOrderId(Integer orderId);

}
