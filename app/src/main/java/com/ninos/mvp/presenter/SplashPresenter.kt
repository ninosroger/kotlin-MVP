package com.ninos.mvp.presenter

import com.blankj.utilcode.util.SPUtils
import com.ninos.mvp.bean.User
import com.ninos.mvp.model.SplashModel
import com.ninos.mvp.network.Const
import com.ninos.mvp.presenter.base.BasePresenter
import com.ninos.mvp.ui.activity.NavigateActivity
import com.ninos.mvp.ui.activity.RecyclerViewDemoActivity
import com.ninos.mvp.view.SplashView

/**
 * Created by ninos on 2017/6/7.
 */
class SplashPresenter : BasePresenter<SplashModel, SplashView>(), SplashView.SplashModelView {
    override fun setModel() = SplashModel(this)

    fun navigate() {
        if (!SPUtils.getInstance(Const.SHAREDPREFERENCES_NAME_FOR_APP).getBoolean(Const.SHAREDPREFERENCES_APP_NODE_ISNOTFIRST))
            view!!.startActivity(NavigateActivity::class.java)
        else {
            view!!.startActivity(RecyclerViewDemoActivity::class.java)
        }
    }

    override fun loginSuccess(user: User) {
    }

    override fun loginError(msg: String) {
        view!!.error(msg)
    }
}