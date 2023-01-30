package cn.bnuz.mystore.base

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding : VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initView(binding)
        initData()
    }

    abstract fun getViewBinding(): VB


    open fun initData(){

    }
    open fun initView(binding: VB){

    }

}