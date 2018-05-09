package com.ninos.mvp.view

import com.ninos.mvp.bean.User
import com.ninos.mvp.view.base.BaseView

/**
 * Created by ninos on 2017/6/7.
 */
interface SplashView : BaseView {
    fun startAnim()
    fun success(user: User)
    fun error(msg: String)

    interface SplashModelView {
        fun loginSuccess(user: User)
        fun loginError(msg: String)
    }
}