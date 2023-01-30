package cn.bnuz.mystore

import android.app.Application

class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        //Log.d("MainApplication","start")
        ApiRepository.initialize(this)
    }
}