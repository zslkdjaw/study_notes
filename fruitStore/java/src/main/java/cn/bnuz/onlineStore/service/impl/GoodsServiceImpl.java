package cn.bnuz.onlineStore.service.impl;

import cn.bnuz.onlineStore.dao.GoodsDao;
import cn.bnuz.onlineStore.entity.Goods;
import cn.bnuz.onlineStore.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Zhang Hao
 * @create 2022-11-23-21:08
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {
}
