package com.ninos.mvp.presenter.base

import com.ninos.mvp.bean.Res
import com.ninos.mvp.model.base.BaseModel
import com.ninos.mvp.view.base.LoadMoreView

/**
 * Created by ninos on 2017/6/1.
 */
abstract class RecyclerViewPresenter<M : BaseModel, V : LoadMoreView> : BasePresenter<M, V>() {

    /**
     * 获取数据
     * @param page
     * @param count
     */
    abstract fun getData(page: Int, count: Int)

    /**
     * 设置数据状态
     * @param page
     * @param count
     * @param res
     */
    fun setDataStatus(page: Int, count: Int, res: Res<*>) {
        if (true) {
            //没有更多了
            view.noMore()
        } else {
            //还有更多
            view.hasMore()
        }

    }
}