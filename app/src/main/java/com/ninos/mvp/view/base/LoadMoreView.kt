package com.ninos.mvp.view.base

/**
 * Created by ninos on 2017/6/1.
 */
interface LoadMoreView : BaseView {
    fun loadMore()
    fun noMore()
    fun hasMore()
    fun loadMore(tip: String)
    fun hasMore(tip: String)
    fun noMore(tip: String)
}