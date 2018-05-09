package com.ninos.mvp.presenter

import com.ninos.mvp.model.ItemContentModel
import com.ninos.mvp.presenter.base.BasePresenter
import com.ninos.mvp.view.ItemContentView

/**
 * Created by ninos on 18-5-8.
 */
class ItemContentPresenter : BasePresenter<ItemContentModel, ItemContentView>() {
    override fun setModel(): ItemContentModel = ItemContentModel(this)
}