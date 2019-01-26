package com.ninos.mvp.presenter

import com.ninos.mvp.model.RecyclerViewDemoModel
import com.ninos.mvp.presenter.base.RecyclerViewPresenter
import com.ninos.mvp.view.RecyclerViewDemoView

/**
 * Created by ninos on 2017/6/14.
 */
class RecyclerViewDemoPresenter : RecyclerViewPresenter<RecyclerViewDemoModel, RecyclerViewDemoView>() {
    override fun getData(page: Int, count: Int) {

    }

    override fun setModel() = RecyclerViewDemoModel()
}