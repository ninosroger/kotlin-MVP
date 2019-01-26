package com.ninos.mvp.ui.fragment

import android.view.View
import com.ninos.mvp.R
import com.ninos.mvp.presenter.SplashPresenter
import com.ninos.mvp.presenter.base.BasePresenter
import com.ninos.mvp.ui.fragment.base.BaseFragment

/**
 * Created by ninos on 2017/6/10.
 */
class NavigateOneFragment : BaseFragment<BasePresenter<*, *>>() {
    companion object {
        fun newInstance(): NavigateOneFragment = Holder.instance
    }

    private object Holder {
        val instance: NavigateOneFragment = NavigateOneFragment()
    }

    override fun provideLayoutId(): Int = R.layout.fragment_navigate_one

    override fun initListeners() {
    }

    override fun initThings(view: View) {
    }

    override fun createPresenter(): BasePresenter<*, *> = SplashPresenter()

}