package com.ninos.mvp.widget

import android.support.multidex.MultiDexApplication
import com.blankj.utilcode.util.Utils

/**
 * Created by ninos on 2017/6/1.
 */
class Application : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(applicationContext)
    }
}