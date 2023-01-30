package cn.bnuz.onlineStore.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author Zhang Hao
 * @create 2022-11-27-11:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("order_tb")
public class Order {
    private Integer orderId;
    private Integer userId;
    private Double orderPrice;
    private Timestamp orderTime;
    public Order(Car car){
        orderId = car.getCarId();
        userId = car.getUserId();
        orderPrice = car.getPrice();
    }

}
