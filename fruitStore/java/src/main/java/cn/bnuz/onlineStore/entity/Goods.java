package cn.bnuz.onlineStore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zhang Hao
 * @create 2022-11-23-15:59
 */
@Data
@TableName("goods_tb")
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    @TableId(type = IdType.AUTO)
    private Integer goodsId ;
    private String goodsName;
    private Float goodsPrice;
    private Integer goodsStocks;
    private String goodsDescription;
    private String filePath;
}
