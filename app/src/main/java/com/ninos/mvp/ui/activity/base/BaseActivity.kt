package com.ninos.mvp.ui.activity.base

import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.ninos.mvp.presenter.base.BasePresenter
import com.ninos.mvp.view.base.BaseView
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import butterknife.ButterKnife
import com.zhy.autolayout.AutoLayoutActivity

/**
 * Created by ninos on 2017/5/27.
 */
abstract class BaseActivity<P : BasePresenter<*, *>> : AutoLayoutActivity(), BaseView {

    var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        ButterKnife.bind(this)
        this.presenter = createPresenter()
        initAttachView()
        initThings(savedInstanceState)
        initListeners()
    }

    /**
     * 获取布局文件
     */
    protected abstract fun provideLayoutId(): Int

    /**
     * attachView
     */
    abstract fun initAttachView()

    /**
     * 初始化事件监听者
     */
    abstract fun initListeners()

    /**
     * 初始化
     */
    protected abstract fun initThings(savedInstanceState: Bundle?)

    /**
     * 绑定Presenter
     */
    abstract fun createPresenter(): P?

    /**
     * 显示Toast
     */
    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    /**
     * 显示Snackbar
     */
    override fun showSnackbar(text: String) {
        Snackbar.make(currentFocus, text, Snackbar.LENGTH_SHORT).show()
    }

    /**
     * 启动Activity
     */
    override fun startActivity(c: Class<*>) {
        startActivity(Intent(getContext(), c))
    }

    /**
     * 启动Activity
     */
    fun startActivity(c: Class<*>, bundle: Bundle) {
        startActivity(Intent(getContext(), c).putExtra("data", bundle))
    }

    /**
     * 获取当前Context
     */
    override fun getContext(): Context = this

    /**
     * 显示输入法界面
     */
    override fun showSoftInput(v: View) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(v, InputMethodManager.SHOW_FORCED)
    }

    /**
     * 隐藏输入法界面
     */
    override fun hideSoftMethod(v: View) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }

}