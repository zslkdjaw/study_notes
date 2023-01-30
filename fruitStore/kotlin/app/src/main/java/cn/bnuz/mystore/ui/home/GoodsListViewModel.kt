package cn.bnuz.mystore.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cn.bnuz.mystore.ApiRepository
import cn.bnuz.mystore.entity.Goods

class GoodsListViewModel : ViewModel(){

    val apiRepository = ApiRepository.get()
    var goods_list : LiveData<List<Goods>> = apiRepository.getGoods()
}