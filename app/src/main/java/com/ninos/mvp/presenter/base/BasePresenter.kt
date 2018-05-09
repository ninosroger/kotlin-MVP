package com.ninos.mvp.presenter.base

/**
 * Created by ninos on 2017/5/27.
 */
abstract class BasePresenter<M,V> {
    protected var view: V? = null
    protected var model: M? = null
    fun attachView(view: V) {
        this.view = view
        model = setModel()
    }
    abstract fun setModel():M
}
