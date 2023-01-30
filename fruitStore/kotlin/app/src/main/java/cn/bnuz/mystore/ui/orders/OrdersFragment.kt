package cn.bnuz.mystore.ui.orders


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.bnuz.mystore.MainActivity
import cn.bnuz.mystore.R
import cn.bnuz.mystore.base.BaseFragment

import cn.bnuz.mystore.entity.Order
import cn.bnuz.mystore.entity.User
import java.text.SimpleDateFormat

private const val TAG = "OrdersFragment"
class OrdersFragment : BaseFragment(){
    override fun getLayoutRestId(): Int {
        return R.layout.fragment_orders
    }
    private lateinit var user : User
    //RecycleView 对象
    lateinit var orderRecyclerView: RecyclerView
    // Adapter 对象
    private var adapter : OrderAdapter ?=  OrderAdapter(emptyList())
    //viewModel 对象
    private val orderViewModel : OrderListViewModel by lazy{
        ViewModelProvider(this)[OrderListViewModel::class.java]
    }
    private lateinit var  searchView : SearchView

    override fun initView(rootView: View?) {
        orderRecyclerView = rootView?.findViewById(R.id.order_recycler_view) as  RecyclerView
        orderRecyclerView.layoutManager = LinearLayoutManager(context)
        searchView = rootView.findViewById(R.id.searchView)
        if (user.userId != -1){
            orderViewModel.initOrderList(user.userId as Int)
            orderViewModel.order_list.observe(
                this.viewLifecycleOwner,
                Observer { order_list ->order_list?.let {
                    //Log.d(TAG,goods_list.size.toString())
                    updateUi(order_list)
                }
                })
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(keyWord: String): Boolean {
                //当提交了输入时的操作
                return false
            }
            override fun onQueryTextChange(keyWord: String): Boolean {
                val filterList = filter(keyWord)
                updateUi(filterList)
                return false
            }
        })
    }

    private fun updateUi(orderList : List<Order>){
        adapter = OrderAdapter(orderList)
        orderRecyclerView.adapter = adapter
    }

    private inner class OrderHolder(view : View) : RecyclerView.ViewHolder(view)  , View.OnClickListener{
        val idTextView : TextView  = view.findViewById(R.id.order_id)
        val timeTextView : TextView = view.findViewById(R.id.order_time)
        val payTextView : TextView = view.findViewById(R.id.order_pay)
        private lateinit var order: Order
        init {
            itemView.setOnClickListener(this)
        }
        //数据绑定至itemView
        fun bind(order: Order) {
            this.order = order
            idTextView.text = String.format("%06dZHNB",this.order.orderId)
            val format = SimpleDateFormat("yyyy年-MM月-dd号 HH:mm")
            val ss = format.format(order.orderTime)
            timeTextView.text = ss
            payTextView.text = this.order.orderPrice.toString()+" 元"
        }

        override fun onClick(v: View?) {
           if(v != null){
               val intent = Intent()
               intent.setClass(v.context,OrderDetailActivity::class.java)
               intent.putExtra("order",order)
               orderViewModel.getGoods(order.orderId)
               startActivity(intent)
           }
        }
    }
    private inner class OrderAdapter(var orderList : List<Order> ) : RecyclerView.Adapter<OrderHolder>(){
        override fun onBindViewHolder(holder: OrderHolder, position: Int) {
            val order = orderList[position]
            holder.bind(order)
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
            val view = layoutInflater.inflate(R.layout.list_item_order , parent , false)
            return OrderHolder(view)
        }
        //设置RecycleView会创建item的个数
        override fun getItemCount() = orderList.size
    }
    //登录后页面初始化
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val paren = context as (MainActivity)
        user  = paren.getLoginState()
    }
    // fragment页面跳转数据变化
    override fun onResume() {
        super.onResume()
        val paren = context as (MainActivity)
        user  = paren.getLoginState()

    }
    private fun filter(keyWord: String): List<Order> {
        // 过滤原本的列表，返回一个新的列表
        val filterList = mutableListOf<Order>()

        for (l in orderViewModel.order_list.value!!) {
            if (l.orderId.toString().contains(keyWord) || l.orderPrice.toString().contains(keyWord)
                || l.orderTime.toString().contains(keyWord)){
                filterList.add(l)
            }

        }
        //Log.d(TAG,filterList.toString())
        //Log.d(TAG,filterList.size.toString())
        return filterList
    }


}