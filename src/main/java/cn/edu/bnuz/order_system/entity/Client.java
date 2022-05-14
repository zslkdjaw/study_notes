package cn.edu.bnuz.order_system.entity;

import lombok.Data;

/**
 * @author
 * @create 2022-05-07-15:13
 */
@Data
public class Client {
    private int clientId ;
    private String clientName;
    private String sex;
    private int age;
    public Client(int id, String clientName, String sex, int age) {
        this.clientId = id;
        this.clientName = clientName;
        this.sex = sex;
        this.age = age;
    }
    public Client(){

    }
    public Client(int clientId){
        this.clientId = clientId;
    }
}
