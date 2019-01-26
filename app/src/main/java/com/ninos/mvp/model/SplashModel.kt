package com.ninos.mvp.model

import com.ninos.mvp.bean.Res
import com.ninos.mvp.model.base.BaseModel
import com.ninos.mvp.network.Net
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by ninos on 2017/6/7.
 */
class SplashModel : BaseModel() {
    fun Login(mob: String, pass: String, success: (data: Res<Any>) -> Unit, error: () -> Unit) {
        val subscription: Subscription = Net.getService()
                .Login(mob, pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ next -> success(next) },
                        { error() },
                        { })
        addSubscription(subscription)
    }
}