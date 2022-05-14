package cn.edu.bnuz.order_system.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @create 2022-05-07-15:20
 */
@Data
public class Order {
    private int orderId;
    private int clientId;
    private String clientName;
    private Date orderDate;
    private int payment;
}
