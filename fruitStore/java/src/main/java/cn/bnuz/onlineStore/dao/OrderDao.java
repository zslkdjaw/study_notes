package cn.bnuz.onlineStore.dao;

import cn.bnuz.onlineStore.entity.Goods;
import cn.bnuz.onlineStore.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-11-27-11:24
 */
@Mapper
public interface OrderDao extends BaseMapper<Order> {
    @Select("select * from car_info_tb ,goods_tb " +
            "where car_info_tb.goods_id = goods_tb.goods_id " +
            "and car_info_tb.car_id = #{order_id}  ")
    List<Goods> getGoodsByOrderId(Integer orderId);

}
