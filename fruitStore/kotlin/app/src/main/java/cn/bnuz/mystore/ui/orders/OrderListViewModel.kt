package cn.bnuz.mystore.ui.orders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.bnuz.mystore.ApiRepository
import cn.bnuz.mystore.entity.Order

class OrderListViewModel : ViewModel(){

    val apiRepository = ApiRepository.get()
    var order_list  = MutableLiveData<List<Order>>()
    fun initOrderList(userId : Int){
        order_list = apiRepository.getAllOrders(userId) as MutableLiveData<List<Order>>
    }
    fun getGoods(orderId : Int){
        apiRepository.getGoodsByOrderId(orderId)
    }
}