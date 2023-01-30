package cn.bnuz.mystore.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cn.bnuz.mystore.ApiRepository
import cn.bnuz.mystore.entity.Car
import cn.bnuz.mystore.entity.Goods
import cn.bnuz.mystore.entity.Order
import cn.bnuz.mystore.entity.User
import org.greenrobot.eventbus.EventBus
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

private const val  TAG = "ServiceManager"

class ServiceManager  {
    private val serviceApi :ServiceApi
    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://4a8d619613.imdo.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        serviceApi = retrofit.create(ServiceApi::class.java)
    }

    //获取所有商品
    fun getGoods(): MutableLiveData<List<Goods>>{
        val responseData : MutableLiveData<List<Goods>> = MutableLiveData()
        val request  = serviceApi.getAll()

        request.enqueue(object  : Callback<List<Goods>>{
            override fun onResponse(call: Call<List<Goods>>, response: Response<List<Goods>>) {
                Log.d(TAG,"receive data")
                Log.d(TAG,response.body().toString())
                responseData.value = response.body()!!
            }
            override fun onFailure(call: Call<List<Goods>>, t: Throwable) {
                Log.d(TAG,"failed to get data")
            }
        })
        return responseData
    }
    //登录 通过账号和密码实现
    fun login( user: User) : LiveData<Boolean> {
        val responseData : MutableLiveData<Boolean> = MutableLiveData()
        val request = serviceApi.login(user)

        request.enqueue(object  : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val u = response.body()
                if (u != null) {
                    ApiRepository.get().record(u)
                }
                EventBus.getDefault().postSticky(u)
                getShopCar(u?.userId)
                responseData.value = true
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d(TAG,"fail")
                responseData.value = false
            }
        })
        return responseData
    }
    //获取购物车
    fun getShopCar(userId: Int?) : LiveData<Car>{
        val data = MutableLiveData<Car>()
        val call = serviceApi.getShopCar(userId!!)
        call.enqueue(object : Callback<Car>{
            override fun onResponse(call: Call<Car>, response: Response<Car>) {
                Log.d(TAG,response.toString())
                Log.d(TAG,response.body().toString())
                EventBus.getDefault().postSticky(response.body())
                data.value = response.body()
            }

            override fun onFailure(call: Call<Car>, t: Throwable) {
                Log.d(TAG,"fail getShopCar")
            }

        })
        return data
    }
    //添加到购物车
    fun addGoodsToCar(carId: Int , goodsId : Int)  {
        val call = serviceApi.addGoodsToCar(carId,goodsId)
        call.enqueue(object  : Callback<Boolean>{
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                Log.d(TAG,"success add to car")
            }
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.d(TAG,"fail add to ShopCar")
            }
        })
    }
    //从购物车移除
    fun removeGoodsToCar(carId: Int , goodsId : Int)  {
        val call = serviceApi.removeGoodsToCar(carId,goodsId)
        call.enqueue(object  : Callback<Boolean>{
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                Log.d(TAG,"success remove to car")
            }
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.d(TAG,"fail remove to ShopCar")
            }
        })
    }
    //获取用户所有订单
    fun getAllOrders(userId : Int) : LiveData<List<Order>>{
        val listOrder   = MutableLiveData<List<Order>> ()
        val call = serviceApi.getAllOrders(userId)
        call.enqueue(object : Callback<List<Order>>{
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                listOrder.value = response.body()
                Log.d(TAG,response.body().toString())
                Log.d(TAG,"receive order list")
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                Log.d(TAG,"fail receive order list")
            }
        })
        return listOrder
    }
    //提交订单
    fun submitOrder(car: Car) : LiveData<Int>  {
        val liveData  = MutableLiveData<Int>()
        val call = serviceApi.submitOrder(car)
        call.enqueue(object : Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                Log.d(TAG,"receive new carID "+response.body().toString())
                liveData.value = response.body()
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.d(TAG,"fail getnew carID ")
            }
        })
        return liveData
    }
    fun getGoodsByOrderId(orderId : Int) {
        val call = serviceApi.getGoodsByOrderId(orderId);
        call.enqueue(object :Callback<List<Goods>>{
            override fun onResponse(call: Call<List<Goods>>, response: Response<List<Goods>>) {
                EventBus.getDefault().postSticky(response.body())
                Log.d(TAG,"success get Goods by orderId ")
            }

            override fun onFailure(call: Call<List<Goods>>, t: Throwable) {
                Log.d(TAG,"fail get Goods by orderId ")
            }
        })
    }




}