package cn.bnuz.mystore.api

import cn.bnuz.mystore.entity.Car
import cn.bnuz.mystore.entity.Goods
import cn.bnuz.mystore.entity.Order
import cn.bnuz.mystore.entity.User
import retrofit2.Call
import retrofit2.http.*

interface ServiceApi {

    //获取所有商品
    @GET("goods")
    fun getAll() : Call<List<Goods>>
    //检验登录
    @POST("users/login")
    fun login(@Body user : User) : Call<User>
    //获取用户的购物车
    @GET("users/car/{userId}")
    fun getShopCar(@Path("userId") userId:Int) : Call<Car>
    //购物车添加商品
    @PUT("users/car/{carId}/{goodsId}")
    fun addGoodsToCar(@Path("carId") carId : Int,@Path("goodsId") goodsId :Int) : Call<Boolean>
    //购物车移除商品
    @DELETE("users/car/{carId}/{goodsId}")
    fun removeGoodsToCar(@Path("carId") carId : Int,@Path("goodsId") goodsId :Int) : Call<Boolean>
    //提交订单
    @PUT("users/orders")
    fun submitOrder(@Body car : Car) : Call<Int>
    //查询订单
    @GET("users/orders/{userId}")
    fun getAllOrders(@Path("userId") userId : Int ) : Call<List<Order>>
    @GET("users/goods/{orderId}")
    fun getGoodsByOrderId(@Path("orderId") orderId : Int) :Call<List<Goods>>
}