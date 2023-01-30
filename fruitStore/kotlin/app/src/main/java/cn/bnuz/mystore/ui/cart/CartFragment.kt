package cn.bnuz.mystore.ui.cart

import android.util.Log

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bnuz.mystore.MainActivity
import cn.bnuz.mystore.R
import cn.bnuz.mystore.base.BaseFragment
import cn.bnuz.mystore.entity.Car
import cn.bnuz.mystore.entity.Goods
import cn.bnuz.mystore.entity.User
import org.greenrobot.eventbus.EventBus

private const val TAG = "CartFragment"
class CartFragment  : BaseFragment(){
   override fun getLayoutRestId(): Int {
        return R.layout.fragment_cart
    }
    private lateinit var user : User
    private lateinit var car : Car
    //RecycleView 对象
    lateinit var goodsRecyclerView: RecyclerView
    // Adapter 对象
    private var adapter : GoodsAdapter ?=  GoodsAdapter(emptyList())
    //viewModel 对象
    private val carViewModel : CarViewModel by lazy{
        ViewModelProvider(this)[CarViewModel::class.java]
    }
    private lateinit var btnCount : Button
    private  lateinit var priceTextView: TextView
    private lateinit var map : LinkedHashMap<String,Int>

    override fun initView(rootView: View?) {
        btnCount = rootView!!.findViewById(R.id.count)
        btnCount.setOnClickListener {
            if (this.user.userId != -1 ){
                if(this.car.goodsList.isEmpty()){
                    Toast.makeText(activity," 购物车为空,无法结算! " , Toast.LENGTH_SHORT).show()
                }else{
                    balance()
                }
            }else{
                Toast.makeText(activity," 请先登录! " , Toast.LENGTH_SHORT).show()
            }
        }
        priceTextView  = rootView.findViewById(R.id.count_price)
        goodsRecyclerView = rootView.findViewById(R.id.car_recycler_view) as RecyclerView
        priceTextView.text = "合计: ¥ 0.00"

        goodsRecyclerView.layoutManager = LinearLayoutManager(context)
        this.user = User()
        this.car = Car()

    }

    override fun initData() {
        map = mapOf("banana" to R.drawable.banana ,"apple" to R.drawable.apple,
            "dragon" to R.drawable.dragon , "peach" to R.drawable.peach,
            "pineapple" to R.drawable.pineapple , "spicy" to R.drawable.spicy ,
            "watermelon" to R.drawable.watermelon , "potato" to R.drawable.potato)
                as LinkedHashMap<String, Int>
    }
    private fun updateUi(goodsList : List<Goods>){
        adapter = GoodsAdapter(goodsList)
        goodsRecyclerView.adapter = adapter
        priceTextView.text = "合计: ¥"+car.price.toString().format(".2d%")
    }
    private inner class GoodsHolder(view : View) : RecyclerView.ViewHolder(view)  {
        val titleTextView : TextView = itemView.findViewById(R.id.goods_title)
        val priceTestView : TextView = itemView.findViewById(R.id.goods_price)
        val stocksTextView : TextView = itemView.findViewById(R.id.goods_stocks)
        val srcImageView : ImageView = itemView.findViewById(R.id.goods_image)

        private lateinit var goods: Goods

        //数据绑定至itemView
        fun bind(goods: Goods) {
            this.goods = goods
            titleTextView.text = this.goods.goodsName
            priceTestView.text = "¥ "+this.goods.goodsPrice.toString()
            stocksTextView.text = "库存: "+this.goods.goodsStocks.toString()
            map[this.goods.filePath]?.let { srcImageView.setImageResource(it) }
        }
    }
    private inner class GoodsAdapter(var goodsList : List<Goods> ) : RecyclerView.Adapter<GoodsHolder>(){
        //Adapter指引Holder到具体的位置Position进行bind()
        //数据绑定至具体的RecycleView位置
        override fun onBindViewHolder(holder: GoodsHolder, position: Int) {
            val goods = goodsList[position]
            holder.itemView.setOnLongClickListener {
                car.goodsList -= goods
                car.price -= goods.goodsPrice
                //后台请求更新数据
                carViewModel.removeCar(car.carId,goods.goodsId)
                priceTextView.text = String.format("%.2f",car.price)
                EventBus.getDefault().postSticky("car")
                Log.d(TAG,goods.toString())
                //前台更新界面
                updateUi(car.goodsList)
                true
            }
            holder.bind(goods)

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsHolder {
            val view = layoutInflater.inflate(R.layout.list_item_good , parent , false)
            return GoodsHolder(view)
        }
        //设置RecycleView会创建item的个数
        override fun getItemCount() = goodsList.size

    }
    // fragment页面跳转数据变化
    override fun onResume() {
        super.onResume()
        val paren = context as (MainActivity)
        user  = paren.getLoginState()
        if(this.user.userId != -1){

            this.user.userId?.let { carViewModel.getShopCar(it) }
            carViewModel.car.observe(
                this.viewLifecycleOwner,
                Observer { car ->
                    car?.let{
                        this.car = car
                        updateUi(car.goodsList)
                        priceTextView.text = "合计: ¥"+car.price.toString().format(".2d%")
                    }
                })
        }
    }
    private fun balance(){
        carViewModel.car_id.value = this.car.carId
        carViewModel.submit(this.car)
        carViewModel.car_id.observe(this.viewLifecycleOwner, Observer{ newCarId ->
            this.car.carId = newCarId
            this.car.goodsList = emptyList()
            this.car.price = 0.00;
            updateUi(this.car.goodsList)
            Log.d(TAG,this.car.toString())
            this.priceTextView.text = "合计: ¥"+String.format("%.2f",car.price)
            EventBus.getDefault().postSticky(this.car)
            Toast.makeText(activity," 结算成功! " , Toast.LENGTH_SHORT).show()
        })
    }

}