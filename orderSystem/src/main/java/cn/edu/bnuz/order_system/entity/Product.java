package cn.edu.bnuz.order_system.entity;

import lombok.Data;

/**
 * @author
 * @create 2022-05-07-15:16
 */

@Data
public class Product {
    private int productId;
    private String productName;
    private float price;
    private int stock;
    private int quantity;
    public Product(){

    }
    public Product(int id){
        this.productId = id;
    }

    public Product(int id, String productName, int pr, int st) {
        this.productId = id;
        this.productName = productName;
        this.price = pr;
        this.stock = st;
    }
}
