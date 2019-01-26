package com.ninos.mvp.ui.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.blankj.utilcode.util.SPUtils
import com.ninos.mvp.R
import com.ninos.mvp.adapter.NavigateViewPagerAdapter
import com.ninos.mvp.network.Const
import com.ninos.mvp.presenter.SplashPresenter
import com.ninos.mvp.presenter.base.BasePresenter
import com.ninos.mvp.ui.activity.base.BaseActivity
import com.ninos.mvp.ui.fragment.NavigateOneFragment
import com.ninos.mvp.ui.fragment.NavigateThreeFragment
import com.ninos.mvp.ui.fragment.NavigateTwoFragment

/**
 * Created by ninos on 2017/6/7.
 */
class NavigateActivity : BaseActivity<BasePresenter<*, *>>(), View.OnTouchListener, GestureDetector.OnGestureListener {
    lateinit var viewPager: ViewPager
    lateinit var gestureDetecotr: GestureDetector

    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean = false

    override fun onDown(e: MotionEvent?): Boolean = false

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        if (e1!!.x - e2!!.x > 120)
            if (viewPager.currentItem == 2) {
                SPUtils.getInstance(Const.SHAREDPREFERENCES_NAME_FOR_APP).put(Const.SHAREDPREFERENCES_APP_NODE_ISNOTFIRST, true)
                startActivity(RecyclerViewDemoActivity::class.java)
            }
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean = false

    override fun onLongPress(e: MotionEvent?) {
    }

    override fun provideLayoutId(): Int = R.layout.activity_navigate

    override fun initListeners() {
    }

    override fun initThings(savedInstanceState: Bundle?) {
        viewPager = findViewById(R.id.viewpager)
        val viewPagerAdapter = NavigateViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(NavigateOneFragment.newInstance(), "")
        viewPagerAdapter.addFragment(NavigateTwoFragment.newInstance(), "")
        viewPagerAdapter.addFragment(NavigateThreeFragment.newInstance(), "")
        viewPager.adapter = viewPagerAdapter
        viewPager.setOnTouchListener(this)
        gestureDetecotr = GestureDetector(this)
    }

    override fun createPresenter(): BasePresenter<*, *> = SplashPresenter()

    override fun onTouch(v: View?, event: MotionEvent?): Boolean = gestureDetecotr.onTouchEvent(event)

}