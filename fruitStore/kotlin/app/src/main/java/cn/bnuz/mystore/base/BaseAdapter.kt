package cn.bnuz.mystore.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import androidx.recyclerview.widget.RecyclerView
import cn.bnuz.mystore.entity.Goods

abstract class BaseAdapter<T>(private val ctx: Context, private val layoutRes: Int, val mData: MutableList<T>)
    : RecyclerView.Adapter<BaseHolder>() {

    private lateinit var mListener: OnItemClickListener


    fun setOnItemClickListener(mListener: OnItemClickListener) {
        this.mListener = mListener
    }

    /**
     * 数据和item的绑定交给了convert()方法，将ViewHolder和position传进去
     */
    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        convert(holder, position)
        holder.itemView.setOnClickListener {
            mListener.onItemClick(position)
        }
    }

    /**
     * 通过layout的id生成ViewHolder对象
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return BaseHolder(LayoutInflater.from(ctx).inflate(layoutRes, parent, false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    /**
     * 用来给具体Adapter实现逻辑的抽象方法
     */
    abstract fun convert(holder: BaseHolder, position: Int)

    /**
     * item的点击事件
     */
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


}