package cn.bnuz.mystore.ui.orders

import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import cn.bnuz.mystore.base.BaseActivity
import cn.bnuz.mystore.databinding.ActivityOrderdetailBinding
import cn.bnuz.mystore.entity.Goods
import cn.bnuz.mystore.entity.Order
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

private const val  TAG = "OrderDetailActivity"
class OrderDetailActivity : BaseActivity<ActivityOrderdetailBinding>(){
    lateinit var list : List<Goods>

    lateinit var textView: TextView



    override fun initView(binding: ActivityOrderdetailBinding) {
        super.initView(binding)
        textView = binding.textV
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    override fun getViewBinding(): ActivityOrderdetailBinding {
        return ActivityOrderdetailBinding.inflate(layoutInflater)
    }
    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }
    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onMessageEvent(list: List<Goods>) {
        this.list = list
        Log.d(TAG,list.toString())
        var str = ""
        for (i in this.list){
            str += "\n"+i.goodsName +"     "+i.goodsPrice
        }
        val data = intent.getSerializableExtra("order") as Order
        str += "\n----------------------------------------------"
        str +="\n 总价格     "+data.orderPrice.toString()+"元 "
        textView.text = str
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}