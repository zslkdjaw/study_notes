package cn.bnuz.onlineStore.controller;

import cn.bnuz.onlineStore.entity.Goods;
import cn.bnuz.onlineStore.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Zhang Hao
 * @create 2022-11-23-21:12
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsServiceImpl goodsService;

    @GetMapping
    public List<Goods> getAll(){
        return goodsService.list();
    }
}
