package com.ninos.mvp.ui.fragment.base

import android.support.v7.widget.RecyclerView
import com.ninos.mvp.adapter.base.BaseAdapter
import com.ninos.mvp.presenter.base.RecyclerViewPresenter
import com.ninos.mvp.view.base.LoadMoreView
import com.ninos.mvp.widget.OnItemClickListener
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.DefaultItemAnimator
import android.view.View
import butterknife.BindView
import com.ninos.mvp.R
import com.ninos.mvp.network.Const

/**
 * Created by ninos on 2017/6/1.
 */
abstract class RecyclerViewFragment<P : RecyclerViewPresenter<*,*>, A : BaseAdapter<*, B, P>, B> : ToolBarFragment<P>(), OnItemClickListener<B>, LoadMoreView {

    @BindView(R.id.recycler_view) lateinit var recyclerView: RecyclerView
    lateinit var adapter: A
    lateinit var layoutManager: RecyclerView.LayoutManager
    var page = 1
    var count = 10

    override fun initThings(view: View) {
        super.initThings(view)
        this.layoutManager = provideLayoutManager()
        recyclerView.layoutManager = layoutManager
        this.adapter = provideAdapter()
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        adapter.setOnItemClickListener(this)
        if (layoutManager is LinearLayoutManager) {
            recyclerView.setOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    onscroll(recyclerView, dx, dy)
                    if (!canScrollDown(recyclerView)) loadMore()
                }
            })
        }
    }

    private fun canScrollDown(recyclerView: RecyclerView): Boolean = ViewCompat.canScrollVertically(recyclerView, 1)

    /**
     * RecycleView监听函数
     * @param recyclerView
     * @param dx
     * @param dy
     */
    protected fun onscroll(recyclerView: RecyclerView, dx: Int, dy: Int){}

    /**
     * @return 提供Adapter
     */
    protected abstract fun provideAdapter(): A

    /**
     * @return 提供LayoutManager
     */
    protected abstract fun provideLayoutManager(): RecyclerView.LayoutManager

    /**
     * 基本的绑定数据
     * @param data
     */
    fun bd(data: ArrayList<B>) {
        if (page == 1) {
            adapter.addDatas(data)
        } else {
            adapter.addMore(data)
        }

    }

    override fun loadMore() {
        if (adapter.getStatus() == Const.STATUS_HASMORE) {
            page++
            presenter.getData(page, count)
            adapter.loading()
        }
    }

    override fun hasMore() {
        adapter.hasMore()
    }

    override fun noMore() {
        adapter.noMore()
    }

    override fun loadMore(tip: String) {
        if (adapter.getStatus() == Const.STATUS_HASMORE) {
            page++
            presenter.getData(page, count)
            adapter.loading(tip)
        }
    }

    override fun hasMore(tip: String) {
        adapter.hasMore(tip)
    }

    override fun noMore(tip: String) {
        adapter.noMore(tip)
    }
}