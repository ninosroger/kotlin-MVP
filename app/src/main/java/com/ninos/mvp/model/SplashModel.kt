package com.ninos.mvp.model

import com.ninos.mvp.model.base.BaseModel
import com.ninos.mvp.network.Net
import com.ninos.mvp.presenter.SplashPresenter
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by ninos on 2017/6/7.
 */
class SplashModel(presenter: SplashPresenter) : BaseModel<SplashPresenter>(presenter) {

    fun Login(mob: String, pass: String) {
        var subscription: Subscription = Net.getService()
                .Login(mob,pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({next -> presenter!!.loginError(next.message)},
                        { presenter!!.loginError("error") },
                        { })
        addSubscription(subscription)
    }
}