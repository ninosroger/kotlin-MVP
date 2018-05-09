package com.ninos.mvp.view.base

import android.content.Context
import android.view.View

/**
 * Created by ninos on 2017/5/27.
 */
interface BaseView {
    fun getContext(): Context
    fun showToast(text: String)
    fun showSnackbar(text: String)
    fun startActivity(c: Class<*>)
    fun showSoftInput(v: View)
    fun hideSoftMethod(v: View)
}