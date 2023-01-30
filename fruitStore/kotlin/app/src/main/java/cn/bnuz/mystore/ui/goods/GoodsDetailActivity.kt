package cn.bnuz.mystore.ui.goods

import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import cn.bnuz.mystore.ApiRepository
import cn.bnuz.mystore.base.BaseActivity
import cn.bnuz.mystore.databinding.ActivityGoodsdetailBinding
import cn.bnuz.mystore.entity.Car
import cn.bnuz.mystore.entity.Goods
import cn.bnuz.mystore.entity.User
import org.greenrobot.eventbus.EventBus

private const val TAG="GoodsDetailActivity"
class GoodsDetailActivity : BaseActivity<ActivityGoodsdetailBinding>(){
    private lateinit var  user : User
    private lateinit var  car : Car
    private lateinit var titleTextView: TextView
    private lateinit var descTextView : TextView
    private lateinit var priceTextView : TextView
    private lateinit var addBtn : Button
    override fun getViewBinding(): ActivityGoodsdetailBinding {
        return ActivityGoodsdetailBinding.inflate(layoutInflater)
    }

    override fun initView(binding: ActivityGoodsdetailBinding) {
        titleTextView = binding.detailTitle
        descTextView = binding.detailDescription
        priceTextView = binding.detailPrice
        addBtn = binding.addCart
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initData() {

        val extraData = intent.getSerializableExtra("data") as Goods
        user = intent.getSerializableExtra("user") as User
        car = intent.getSerializableExtra("car") as Car
        Log.d(TAG,user.toString())
        titleTextView.text = extraData.goodsName
        descTextView.text = extraData.goodsDescription
        priceTextView.text = extraData.goodsPrice.toString()

        addBtn.setOnClickListener{
            if (this.user.userId != -1){
                if ( car.goodsList.contains(extraData)){
                    Log.d(TAG,extraData.toString())
                    Toast.makeText(this," 商品已在购物车 请勿重复点击! ", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this," 添加成功! ", Toast.LENGTH_SHORT).show()
                    ApiRepository.get().addGoodsToCar(car.carId,extraData.goodsId)
                    car.goodsList += extraData
                    car.price += extraData.goodsPrice
                    EventBus.getDefault().postSticky(car)
                    finish()
                }

            }else{
                Toast.makeText(this," 请先登录 ! ", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}