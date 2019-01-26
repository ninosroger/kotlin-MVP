package com.ninos.mvp.ui.fragment.base

import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.ninos.mvp.R
import com.ninos.mvp.presenter.base.BasePresenter


/**
 * Created by ninos on 2017/5/31.
 */
abstract class ToolBarFragment<P : BasePresenter<*, *>> : BaseFragment<P>() {
    private lateinit var toolBar: Toolbar
    protected lateinit var imgBack: ImageView
    protected lateinit var tvTitle: TextView
    private lateinit var appBar: AppBarLayout
    private var mIsHidden = false
    protected lateinit var imgAction: ImageView


    /**
     * @return 标题
     */
    protected abstract fun provideTitle(): CharSequence

    /**
     * 初始化toolbar
     */
    override fun initThings(view: View) {
        initToolBar(view)
    }

    private fun initToolBar(v: View) {
        toolBar = v.findViewById(R.id.toolbar) as Toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolBar)
        imgBack = v.findViewById(R.id.img_back) as ImageView
        tvTitle = v.findViewById(R.id.tv_title) as TextView
        imgAction = v.findViewById(R.id.img_action) as ImageView
        appBar = v.findViewById(R.id.app_bar_layout) as AppBarLayout
        if (canBack()) {

        } else {
            imgBack.visibility = View.GONE
        }
        if (canAction()) {
            imgAction.setOnClickListener { action() }
        } else {
            imgAction.visibility = View.GONE
        }
        tvTitle.text = provideTitle()
    }

    /**
     * Toolbar右边按钮的点击事件
     */
    open fun action() {

    }

    /**
     * @param alpha 设置标题栏的透明度
     */
    protected fun setAppBarAlpha(alpha: Float) {
        appBar.alpha = alpha
    }

    /**
     * 隐藏和显示Toolbar
     */
    protected fun hideOrShowToolbar() {
        appBar.animate()
                .translationY((if (mIsHidden) 0 else -appBar.height).toFloat())
                .setInterpolator(DecelerateInterpolator(2f))
                .start()
        mIsHidden = !mIsHidden
    }

    /**
     * @return 返回按钮是否可以显示
     */
    open fun canBack(): Boolean = false

    /**
     * @return 右边按钮是否显示
     */
    open fun canAction(): Boolean = false
}