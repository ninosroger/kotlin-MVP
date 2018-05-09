package com.ninos.mvp.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ninos.mvp.R
import com.ninos.mvp.adapter.RecyclerViewDemoAdapter
import com.ninos.mvp.bean.DataItem
import com.ninos.mvp.network.Const
import com.ninos.mvp.presenter.RecyclerViewDemoPresenter
import com.ninos.mvp.ui.activity.base.SwipeRecyclerViewActivity
import com.ninos.mvp.view.RecyclerViewDemoView

/**
 * Created by ninos on 2017/6/14.
 */
class RecyclerViewDemoActivity : SwipeRecyclerViewActivity<RecyclerViewDemoPresenter, RecyclerViewDemoAdapter, DataItem>(), RecyclerViewDemoView {

    var dataList = ArrayList<DataItem>()

    /**
     * 是否显示Toolbar返回图标
     */
    override fun canBack(): Boolean = false

    /**
     * 是否显示Toolbar右图标
     */
    override fun canAction(): Boolean = true

    /**
     * 布局文件
     */
    override fun provideLayoutId(): Int = R.layout.base_swipe_recycler_view

    /**
     * Toolbar标题
     */
    override fun provideTitle(): CharSequence = "MVP"

    override fun initThings(savedInstanceState: Bundle?) {
        super.initThings(savedInstanceState)
        dataList.add(DataItem(getString(R.string.butterknife_title), getString(R.string.butterknife_url), getString(R.string.butterknife_content), Const.BUTTER_KNIFE_ICON))
        dataList.add(DataItem(getString(R.string.autolayout_title), getString(R.string.autolayout_url), getString(R.string.autolayout_content), ""))
        dataList.add(DataItem(getString(R.string.glide_title), getString(R.string.glide_url), getString(R.string.glide_content), Const.GLIDE_ICON))
        dataList.add(DataItem(getString(R.string.rxandroid_title), getString(R.string.rxandroid_url), getString(R.string.rxandroid_content), ""))
        dataList.add(DataItem(getString(R.string.okhttp_title), getString(R.string.okhttp_url), getString(R.string.okhttp_content), ""))
        dataList.add(DataItem(getString(R.string.android_util_code_title), getString(R.string.android_util_code_url), getString(R.string.android_util_code_content), Const.ANDROID_UTIL_CODE_ICON))
        dataList.add(DataItem(getString(R.string.retrofit_title), getString(R.string.retrofit_url), getString(R.string.retrofit_content), ""))

        bd(dataList)
    }

    /**
     * Toolbar右图标点击事件
     */
    override fun action() {
        super.action()
        if (adapter!!.span == adapter!!.SPAN_COUNT_TWO) {
            adapter!!.span = adapter!!.SPAN_COUNT_ONE
            imgAction.setImageResource(R.drawable.list_grid)
        } else {
            adapter!!.span = adapter!!.SPAN_COUNT_TWO
            imgAction.setImageResource(R.drawable.list_linear)
        }
        adapter!!.notifyItemRangeChanged(0, adapter!!.itemCount)
    }

    /**
     * 列表item点击事件
     */
    override fun onItemClick(view: View, position: Int, item: DataItem) {
        var bundle = Bundle()
        bundle.putSerializable("itemData", item)
        startActivity(ItemContentActivity::class.java, bundle)
    }

    /**
     * 当前view传递给presenter
     */
    override fun initAttachView() {
        presenter!!.attachView(this)
    }

    /**
     * 事件
     */
    override fun initListeners() {

    }

    /**
     * 下拉刷新后要做的操作
     */
    override fun requestDataRefresh() {
        super.requestDataRefresh()
        refresh(false)
    }

    override fun createPresenter(): RecyclerViewDemoPresenter? = RecyclerViewDemoPresenter()

    override fun provideAdapter(): RecyclerViewDemoAdapter = RecyclerViewDemoAdapter(getContext())

    /**
     * item显示操作
     */
    override fun provideLayoutManager(): RecyclerView.LayoutManager {
        var gridLayoutManager = GridLayoutManager(getContext(), 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int =
                    if (adapter!!.span == adapter!!.SPAN_COUNT_TWO) 1 else 2
        }
        return gridLayoutManager
    }


}
