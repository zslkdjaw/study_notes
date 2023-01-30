package cn.bnuz.mystore.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView : View = inflater.inflate(getLayoutRestId(),container,false)
        initView(rootView)
        initData()
        return rootView
    }

    open fun initView(rootView: View?) {

    }
    open fun initData(){

    }
    abstract fun getLayoutRestId(): Int
}