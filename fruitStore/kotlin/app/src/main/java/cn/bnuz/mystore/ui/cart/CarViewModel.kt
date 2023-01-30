package cn.bnuz.mystore.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.bnuz.mystore.ApiRepository
import cn.bnuz.mystore.entity.Car
import cn.bnuz.mystore.entity.Goods


class CarViewModel : ViewModel(){
    val apiRepository = ApiRepository.get()
    var car = MutableLiveData<Car>()
    var car_id = MutableLiveData<Int>()

    fun submit(car : Car){
        car_id = apiRepository.submitOrder(car) as MutableLiveData<Int>
    }

    fun removeCar(carId : Int ,goodsId : Int){
        apiRepository.removeGoodsFromCar(carId,goodsId)
    }
    fun getShopCar(userId : Int) {
        car = apiRepository.getShopCar(userId) as MutableLiveData<Car>
    }
}