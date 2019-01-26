package com.ninos.mvp.presenter.base

import com.ninos.mvp.model.base.BaseModel
import com.ninos.mvp.view.base.BaseView

/**
 * Created by ninos on 2017/5/27.
 */
abstract class BasePresenter<M : BaseModel, V : BaseView> {
    protected lateinit var view: V
    protected lateinit var model: M
    fun attachView(view: BaseView) {
        this.view = view as V
        model = setModel()
    }

    abstract fun setModel(): M
}
