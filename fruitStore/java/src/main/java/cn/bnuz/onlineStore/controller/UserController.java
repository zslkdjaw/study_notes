package cn.bnuz.onlineStore.controller;

import cn.bnuz.onlineStore.entity.Car;
import cn.bnuz.onlineStore.entity.Goods;
import cn.bnuz.onlineStore.entity.Order;
import cn.bnuz.onlineStore.entity.User;
import cn.bnuz.onlineStore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-11-25-12:35
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public User login(@RequestBody User user){
        return userService.login(user);
    }

    @GetMapping("/car/{userId}")
    public Car getShopCar(@PathVariable Integer userId){
        return userService.getShopCar(userId);
    }
    @PutMapping("/car/{carId}/{goodsId}")
    public Boolean addGoodsToCarById(@PathVariable Integer carId , @PathVariable Integer goodsId){
        userService.addGoodsToCar(carId,goodsId);
        return false;
    }
    @DeleteMapping("/car/{carId}/{goodsId}")
    public Boolean removeGoodsToCarById(@PathVariable Integer carId, @PathVariable Integer goodsId){
        userService.removeGoodsToCar(carId,goodsId);
        return null;
    }
    @GetMapping("/orders/{userId}")
    public List<Order> getAllOrders(@PathVariable Integer userId){
        return userService.getAllOrder(userId);
   }
    @PutMapping("/orders")
    public Integer submitOrder(@RequestBody Car car ){
        return userService.addCarToOrder(car);
    }

    @GetMapping("/goods/{orderId}")
    public List<Goods> getGoodsByOrderId(@PathVariable Integer orderId){
        return userService.getGoodsByOrderId(orderId);
    }

}
