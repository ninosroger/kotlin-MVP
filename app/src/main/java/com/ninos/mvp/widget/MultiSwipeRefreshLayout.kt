package com.ninos.mvp.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet
import com.ninos.mvp.R


/**
 * Created by ninos on 2017/6/1.
 */
class MultiSwipeRefreshLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : SwipeRefreshLayout(context, attrs) {

    private var mCanChildScrollUpCallback: CanChildScrollUpCallback? = null
    private val mForegroundDrawable: Drawable?

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.MultiSwipeRefreshLayout, 0, 0)

        mForegroundDrawable = a.getDrawable(R.styleable.MultiSwipeRefreshLayout_foreground)
        if (mForegroundDrawable != null) {
            mForegroundDrawable.callback = this
            setWillNotDraw(false)
        }
        a.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mForegroundDrawable?.setBounds(0, 0, w, h)
    }

    fun setCanChildScrollUpCallback(canChildScrollUpCallback: CanChildScrollUpCallback) {
        mCanChildScrollUpCallback = canChildScrollUpCallback
    }

    interface CanChildScrollUpCallback {
        fun canSwipeRefreshChildScrollUp(): Boolean 
    }

    override fun canChildScrollUp(): Boolean {
        if (mCanChildScrollUpCallback != null) return mCanChildScrollUpCallback!!.canSwipeRefreshChildScrollUp()
        return super.canChildScrollUp()
    }
}