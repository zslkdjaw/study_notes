package cn.bnuz.mystore

import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import cn.bnuz.mystore.base.BaseActivity
import cn.bnuz.mystore.databinding.ActivityMainBinding
import cn.bnuz.mystore.entity.Car
import cn.bnuz.mystore.entity.User

import com.google.android.material.bottomnavigation.BottomNavigationView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

private const val TAG = "MainActivity"
open class MainActivity : BaseActivity<ActivityMainBinding>(){

    //这个是共享ViewModel
    //private val sharedViewModel: ShellMainSharedViewModel by viewModels()
    var user =  User()
    var car = Car()
    private var isExit: Boolean = false
    private lateinit var  navView: BottomNavigationView

    override fun getViewBinding(): ActivityMainBinding {

        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView(binding: ActivityMainBinding) {

        navView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        //底部显示对象设置
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_shoppingCar,
                R.id.navigation_orders, R.id.navigation_account
            )
        )
        //控制器与显示对象ID绑定
        setupActionBarWithNavController(navController, appBarConfiguration)
        //组件显示与组件控制绑定
        navView.setupWithNavController(navController)

        ApiRepository.get().loginWithDatabase()
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            exitBy2Click() //调用双击退出函数
        return false
    }

    fun exitBy2Click() {
        val handler = Handler()
        if ((!isExit)) {
            isExit = true
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show()
            handler.postDelayed({ isExit = false }, 1000 * 2) //x秒后没按就取消
        } else {
            finish()
            System.exit(0)
        }
    }
    //全局接收广播
    override fun onStart() {
        super.onStart()
        //ApiRepository.get().loginWithDatabase()
        Log.d(TAG,"on starting .....")
        EventBus.getDefault().register(this@MainActivity)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"on stop .....")
        EventBus.getDefault().unregister(this@MainActivity)
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onMessageEvent(user: User) {
        Log.d(TAG,user.toString())
        this.user = user
        Log.d(TAG,this.user.toString())
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onMessageEvent(car: Car) {
        this.car = car
        Log.d(TAG,this.car.toString())
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onMessageEvent(carId: Int) {
        this.car.carId = carId
        this.car.goodsList = emptyList()
        this.car.price = 0.00;
        Log.d(TAG,this.car.toString())
    }
    fun getLoginState(): User{
        return this.user
    }
    fun getUserCar() : Car{
        return this.car
    }
}