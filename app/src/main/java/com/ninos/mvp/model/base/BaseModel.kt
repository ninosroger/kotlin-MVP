package com.ninos.mvp.model.base

import rx.subscriptions.CompositeSubscription
import rx.Subscription

/**
 * Created by ninos on 2017/5/27.
 */
abstract class BaseModel {

    companion object {

        protected var mCompositeSubscription: CompositeSubscription? = null

        private fun getInstance() {
            mCompositeSubscription = Holder.compositeSubscription
        }

        fun unSubscription() {
            if (this.mCompositeSubscription != null) {
                this.mCompositeSubscription!!.unsubscribe()
            }
        }

        fun addSubscription(s: Subscription) {
            if (this.mCompositeSubscription == null) {
                getInstance()
            }
            this.mCompositeSubscription!!.add(s)
        }
    }

    private object Holder {
        val compositeSubscription: CompositeSubscription = CompositeSubscription()
    }

}