package cn.bnuz.onlineStore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zhang Hao
 * @create 2022-11-25-12:29
 */
@Data
@TableName("user_tb")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userPhone;
    private String userAddress;
    @TableField(exist = false)
    private Car car;
}
