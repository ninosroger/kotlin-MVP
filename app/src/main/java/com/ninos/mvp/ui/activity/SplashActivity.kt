package com.ninos.mvp.ui.activity

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.ninos.mvp.R
import com.ninos.mvp.bean.User
import com.ninos.mvp.presenter.SplashPresenter
import com.ninos.mvp.ui.activity.base.BaseActivity
import com.ninos.mvp.view.SplashView

/**
 * Created by ninos on 2017/6/7.
 */
class SplashActivity : BaseActivity<SplashPresenter>(), SplashView {
    lateinit var imgSplash: ImageView

    override fun startAnim() {
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.splash)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationEnd(animation: Animation) {
                presenter.navigate()
            }

            override fun onAnimationRepeat(animation: Animation) {
            }
        })
        imgSplash.startAnimation(animation)
    }

    override fun success(user: User) {
    }

    override fun error(msg: String) {
        showToast(msg)
    }

    override fun provideLayoutId(): Int = R.layout.activity_splash

    override fun initListeners() {
    }

    override fun initThings(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        imgSplash = findViewById(R.id.img_splash)
        startAnim()
    }

    override fun createPresenter(): SplashPresenter = SplashPresenter()

}