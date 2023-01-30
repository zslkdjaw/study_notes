package cn.bnuz.onlineStore.dao;

import cn.bnuz.onlineStore.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Zhang Hao
 * @create 2022-11-25-12:31
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}
