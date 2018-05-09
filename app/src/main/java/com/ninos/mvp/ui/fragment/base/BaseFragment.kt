package com.ninos.mvp.ui.fragment.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.ninos.mvp.presenter.base.BasePresenter
import com.ninos.mvp.view.base.BaseView
import butterknife.ButterKnife
import android.view.ViewGroup
import android.view.LayoutInflater


/**
 * Created by ninos on 2017/5/27.
 */
abstract class BaseFragment<P : BasePresenter<*,*>> : Fragment(), BaseView {

    var presenter: P? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(provideLayoutId(), container, false)
        ButterKnife.bind(this, view)
        this.presenter = createPresenter()
        initAttachView()
        initThings(view)
        initListeners()
        return view
    }

    /**
     * attachView
     */
    abstract fun initAttachView()

    /**
     * 获取布局文件d
     */
    protected abstract fun provideLayoutId(): Int


    /**
     * 初始化事件监听者
     */
    abstract fun initListeners()

    /**
     * 初始化
     */
    protected abstract fun initThings(view: View)

    /**
     * 绑定Presenter
     */
    abstract fun createPresenter(): P?

    /**
     * 显示Toast
     */
    override fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

    /**
     * 显示Snackbar
     */
    override fun showSnackbar(text: String) {
        Snackbar.make(activity.currentFocus, text, Snackbar.LENGTH_SHORT).show()
    }

    /**
     * 启动Actviity
     */
    override fun startActivity(c: Class<*>) {
        startActivity(Intent(context, c))
    }

    /**
     * 获取当前Context
     */
    override fun getContext(): Context = activity

    /**
     * 显示输入法界面
     */
    override fun showSoftInput(v: View) {
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(v, InputMethodManager.SHOW_FORCED)
    }

    /**
     * 隐藏输入法界面
     */
    override fun hideSoftMethod(v: View) {
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)

    }
}