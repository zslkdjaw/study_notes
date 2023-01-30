package cn.bnuz.mystore

import android.content.Context
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import cn.bnuz.mystore.entity.Car
import cn.bnuz.mystore.entity.Goods
import cn.bnuz.mystore.entity.Order
import cn.bnuz.mystore.entity.User
import cn.bnuz.mystore.api.ServiceManager
import cn.bnuz.mystore.database.UserDao
import cn.bnuz.mystore.database.UserDatabase
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.Executors

private const val DATABASE_NAME = "user-database"
class ApiRepository private constructor(context : Context){

    //database
    private val database : UserDatabase = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        DATABASE_NAME).build()
    private val userDao = database.UserDao()
    private val executor = Executors.newSingleThreadExecutor()
    //network
    private val serviceManager : ServiceManager = ServiceManager()

    fun getGoods(): MutableLiveData<List<Goods>>{
        return  serviceManager.getGoods()
    }
    fun record(user : User){
        executor.execute {
            userDao.addUser(user)
            var list = userDao.getUser()
            Log.d(DATABASE_NAME,list.toString())
        }
    }
    fun erasureAll(){
        executor.execute {
            userDao.deleteAll()
            var list = userDao.getUser()
            Log.d(DATABASE_NAME,list.toString())
        }
    }
    fun erasure(user : User){
        executor.execute{
            userDao.deleteUser(user)
            var list = userDao.getUser()
            Log.d(DATABASE_NAME,list.toString())
        }
    }
    fun loginWithDatabase(){
        executor.execute {
            val user = userDao.getUser()
            if (user.isNotEmpty()){
                EventBus.getDefault().postSticky(user[0])
            }
            Log.d(DATABASE_NAME,user.toString())
        }
    }
    fun login( user: User) : LiveData<Boolean> {
        return serviceManager.login(user)
    }

    fun addGoodsToCar(carId: Int , goodsId : Int)  {
        serviceManager.addGoodsToCar(carId,goodsId)
    }
    fun removeGoodsFromCar(carId: Int , goodsId : Int)  {
        serviceManager.removeGoodsToCar(carId,goodsId)
    }
    fun getAllOrders(userId : Int) : LiveData<List<Order>>{
        return serviceManager.getAllOrders(userId)
    }
    fun submitOrder(car: Car) : LiveData<Int>  {
        return serviceManager.submitOrder(car)
    }
    fun getGoodsByOrderId(orderId : Int) {
        serviceManager.getGoodsByOrderId(orderId)
    }
    fun getShopCar(userId : Int) : LiveData<Car>{
        return serviceManager.getShopCar(userId)
    }
    companion object {
        private var INSTANCE: ApiRepository? = null
        fun initialize(context : Context) {
            if (INSTANCE == null) {
                INSTANCE = ApiRepository(context)
            }
        }
        fun get(): ApiRepository {
            return INSTANCE ?:
            throw IllegalStateException("ApiRepository must be initialized")
        }
    }

}