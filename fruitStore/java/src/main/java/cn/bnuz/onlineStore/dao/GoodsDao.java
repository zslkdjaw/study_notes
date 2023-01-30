package cn.bnuz.onlineStore.dao;

import cn.bnuz.onlineStore.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-11-23-16:02
 */

@Mapper
public interface GoodsDao extends BaseMapper<Goods> {
    @Select("select * \n" +
            "from goods_tb,car_tb,car_info_tb \n" +
            "where car_info_tb.car_id = car_tb.car_id\n" +
            "and car_info_tb.goods_id = goods_tb.goods_id and car_tb.user_id = #{id};")
    List<Goods> selectCarByUserId(Integer id);
    @Select("select * " +
            "from goods_tb,car_tb,car_info_tb " +
            "where car_info_tb.car_id = car_tb.car_id and car_info_tb.goods_id = goods_tb.goods_id and car" +
            "_tb.user_id = #{userId} and car_info_tb.goods_id = #{goodsId};")
    Goods isExistCar(Integer userId, Integer goodsId);

    @Insert("insert into car_info_tb values ( #{carId} , #{goodsId}  )")
    void insertGoodsToCar( Integer carId , Integer goodsId );

    @Delete("delete from car_info_tb where car_id = #{carId} and goods_id = #{goodsId} ")
    boolean deleteGoodsToCar( Integer carId , Integer goodsId );

}
