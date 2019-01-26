package com.ninos.mvp.ui.fragment

import android.view.View
import android.widget.ImageView
import com.blankj.utilcode.util.SPUtils
import com.ninos.mvp.R
import com.ninos.mvp.network.Const
import com.ninos.mvp.presenter.SplashPresenter
import com.ninos.mvp.presenter.base.BasePresenter
import com.ninos.mvp.ui.activity.RecyclerViewDemoActivity
import com.ninos.mvp.ui.fragment.base.BaseFragment

/**
 * Created by ninos on 2017/6/10.
 */
class NavigateThreeFragment : BaseFragment<BasePresenter<*, *>>() {
    lateinit var fntClick: ImageView

    companion object {
        fun newInstance(): NavigateThreeFragment = Holder.instance
    }

    private object Holder {
        val instance: NavigateThreeFragment = NavigateThreeFragment()
    }

    override fun provideLayoutId(): Int = R.layout.fragment_navigate_three

    override fun initListeners() {
        fntClick.setOnClickListener { SPUtils.getInstance(Const.SHAREDPREFERENCES_NAME_FOR_APP).put(Const.SHAREDPREFERENCES_APP_NODE_ISNOTFIRST, true) }
        startActivity(RecyclerViewDemoActivity::class.java)
    }

    override fun initThings(view: View) {
        fntClick = view.findViewById(R.id.fnt_click) as ImageView
    }

    override fun createPresenter(): BasePresenter<*, *> = SplashPresenter()

}