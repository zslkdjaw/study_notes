package cn.bnuz.mystore.ui.home

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bnuz.mystore.ApiRepository
import cn.bnuz.mystore.MainActivity
import cn.bnuz.mystore.R
import cn.bnuz.mystore.entity.Goods
import cn.bnuz.mystore.base.BaseFragment
import cn.bnuz.mystore.entity.Car
import cn.bnuz.mystore.entity.User
import cn.bnuz.mystore.ui.goods.GoodsDetailActivity

private const val TAG = "HomeFragment"
open class HomeFragment : BaseFragment(){
    private lateinit var user : User
    private lateinit var car : Car
    //RecycleView 对象
     lateinit var goodsRecyclerView: RecyclerView
    // Adapter 对象
    private var adapter : GoodsAdapter ?=  GoodsAdapter(emptyList())
    //viewModel 对象

    private val goodsListViewModel : GoodsListViewModel by lazy{
        ViewModelProvider(this)[GoodsListViewModel::class.java]
    }
    private lateinit var map : LinkedHashMap<String,Int>

    override fun initView(rootView: View?) {

        goodsRecyclerView = rootView?.findViewById(R.id.goods_recycler_view) as RecyclerView
        goodsRecyclerView.layoutManager = LinearLayoutManager(context)
        goodsListViewModel.goods_list.observe(
            this.viewLifecycleOwner,
        Observer { goods_list ->goods_list?.let {
            //Log.d(TAG,goods_list.size.toString())
            updateUi(goods_list)
        }
        })
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
    }

    private inner class GoodsHolder(view : View) : RecyclerView.ViewHolder(view) , View.OnClickListener{
        val titleTextView : TextView = itemView.findViewById(R.id.goods_title)
        val priceTestView : TextView = itemView.findViewById(R.id.goods_price)
        val stocksTextView : TextView = itemView.findViewById(R.id.goods_stocks)
        val srcImageView : ImageView = itemView.findViewById(R.id.goods_image)
        private lateinit var goods: Goods

        init {
            itemView.setOnClickListener(this)
        }
        //数据绑定至itemView
        fun bind(goods: Goods) {
            this.goods = goods
            titleTextView.text = this.goods.goodsName
            priceTestView.text = "¥ "+this.goods.goodsPrice.toString()
            stocksTextView.text = "库存: "+this.goods.goodsStocks.toString()
            Log.d(TAG,this.goods.filePath)
            map[this.goods.filePath]?.let { srcImageView.setImageResource(it) }
        }

        override fun onClick(v: View?) {
//            callbacks?.onCrimeSelected(crime.id)
            val intent = Intent()
            if (v != null) {
                intent.setClass(v.context,GoodsDetailActivity::class.java)
                intent.putExtra("data",goods)
                intent.putExtra("user",user)
                intent.putExtra("car",car)
                startActivity(intent)
            }
            //Log.d(TAG, goods.goodsName)
        }
    }
    private inner class GoodsAdapter(var goodsList : List<Goods>) : RecyclerView.Adapter<GoodsHolder>(){
        //Adapter指引Holder到具体的位置Position进行bind()
        //数据绑定至具体的RecycleView位置
        override fun onBindViewHolder(holder: GoodsHolder, position: Int) {
            val crime = goodsList[position]
            holder.bind(crime)
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsHolder {
            val view = layoutInflater.inflate(R.layout.list_item_good , parent , false)
            return GoodsHolder(view)
        }
        //设置RecycleView会创建item的个数
        override fun getItemCount() = goodsList.size
    }

    override fun getLayoutRestId(): Int {
        return R.layout.fragment_home
    }

    override fun onResume() {
        super.onResume()
        val paren = context as (MainActivity)
        user  = paren.getLoginState()
        car = paren.getUserCar()
        Log.d(TAG, "$user on resume")

    }

}