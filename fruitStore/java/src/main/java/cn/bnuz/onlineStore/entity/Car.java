package cn.bnuz.onlineStore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-11-26-13:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("car_tb")
public class Car {
    Integer carId;
    Integer userId;
    Double price;
    @TableField(exist = false)
    List<Goods> goodsList;
    public Car(Integer carId, Integer userId, Double price) {
        this.carId =carId;
        this.userId = userId;
        this.price = price;
    }
}
