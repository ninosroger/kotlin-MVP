package com.ninos.mvp.ui.fragment.base

import com.ninos.mvp.presenter.base.BasePresenter
import com.ninos.mvp.view.base.SwipeRefreshView
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import butterknife.BindView
import com.blankj.utilcode.util.ConvertUtils
import com.ninos.mvp.R

/**
 * Created by ninos on 2017/6/1.
 */
abstract class SwipeRefreshFragment<P : BasePresenter<*, *>> : ToolBarFragment<P>(), SwipeRefreshView {

    @BindView(R.id.swipe_refresh_layout)
    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private var mIsRequestDataRefresh = false

    override fun initThings(view: View) {
        super.initThings(view)
        trySetupSwipeRefresh()
    }

    private fun trySetupSwipeRefresh() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_3, R.color.refresh_progress_2, R.color.refresh_progress_1)
        mSwipeRefreshLayout.setOnRefreshListener { requestDataRefresh() }
        setProgressViewOffset(false, 0, ConvertUtils.dp2px(24F))
    }

    /**
     * 从数据源获取数据
     */
    fun requestDataRefresh() {
        mIsRequestDataRefresh = true
    }

    override fun refresh(refreshing: Boolean) {
        mIsRequestDataRefresh = refreshing
        if (!mIsRequestDataRefresh) {
            mIsRequestDataRefresh = false
            mSwipeRefreshLayout.postDelayed({ mSwipeRefreshLayout.isRefreshing = false }, 1000)
        } else {
            mSwipeRefreshLayout.isRefreshing = true
        }
    }

    private fun setProgressViewOffset(scale: Boolean, start: Int, end: Int) {
        mSwipeRefreshLayout.setProgressViewOffset(scale, start, end)
    }

    fun isRequestDataRefresh(): Boolean = mIsRequestDataRefresh
}