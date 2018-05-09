package com.ninos.mvp.widget

import android.view.View

/**
 * Created by ninos on 2017/5/31.
 */
interface OnItemClickListener<M> {
    fun onItemClick(view: View, position: Int, item: M)
}