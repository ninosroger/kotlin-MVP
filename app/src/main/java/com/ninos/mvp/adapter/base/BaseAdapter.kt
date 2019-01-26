package com.ninos.mvp.adapter.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ninos.mvp.R
import com.ninos.mvp.network.Const
import com.ninos.mvp.presenter.base.BasePresenter
import com.ninos.mvp.widget.OnItemClickListener

/**
 * Created by ninos on 2017/5/31.
 */
abstract class BaseAdapter<VH : RecyclerView.ViewHolder, B, P : BasePresenter<*, *>> : RecyclerView.Adapter<VH> {

    var mOnItemClickListener: OnItemClickListener<B>? = null   //点击事件
    var data: ArrayList<B> = ArrayList()                    //数据data
    var inflater: LayoutInflater
    var context: Context
    lateinit var presenter: P

    private var STATUS = Const.STATUS_HASMORE
    private var header: View? = null
    private var footer: View? = null
    private var tvStatus: TextView? = null
    var isAddFooter = true

    private fun init() {
        if (isAddFooter) {
            footer = inflater.inflate(R.layout.footer_recycleview, null)
            tvStatus = footer!!.findViewById(R.id.tv_status) as TextView
        }
    }

    constructor(context: Context) {
        this.context = context
        inflater = LayoutInflater.from(context)
        init()
    }

    constructor(context: Context, p: P) {
        this.context = context
        inflater = LayoutInflater.from(context)
        this.presenter = p
        init()
    }

    constructor(context: Context, header: View) {
        this.context = context
        inflater = LayoutInflater.from(context)
        this.header = header
        init()
    }

    constructor(context: Context, p: P, header: View) {
        this.context = context
        inflater = LayoutInflater.from(context)
        this.header = header
        this.presenter = p
        init()
    }

    constructor(context: Context, isAddFooter: Boolean) {
        this.context = context
        inflater = LayoutInflater.from(context)
        this.isAddFooter = isAddFooter
        init()
    }

    constructor(context: Context, p: P, isAddFooter: Boolean) {
        this.context = context
        inflater = LayoutInflater.from(context)
        this.presenter = p
        this.isAddFooter = isAddFooter
        init()
    }

    constructor(context: Context, header: View, isAddFooter: Boolean) {
        this.context = context
        inflater = LayoutInflater.from(context)
        this.header = header
        this.isAddFooter = isAddFooter
        init()
    }

    constructor(context: Context, p: P, header: View, isAddFooter: Boolean) {
        this.context = context
        inflater = LayoutInflater.from(context)
        this.header = header
        this.presenter = p
        this.isAddFooter = isAddFooter
        init()
    }

    override fun getItemViewType(position: Int): Int {
        if (header != null) {
            if (position == Const.VIEW_TYPE_HEADER) return Const.VIEW_TYPE_HEADER
        }
        if (isAddFooter) {
            if (position == itemCount - 1) return Const.VIEW_TYPE_FOOTER
        }
        return Const.VIEW_TYPE_NORMAL
    }

    /**
     * @return ItemView的LayoutId
     */
    abstract fun provideItemLayoutId(): Int

    /**
     * @param parent
     * *
     * @param viewType
     * *
     * @param view
     * *
     * @return 创建ViewHolder
     */
    abstract fun createVH(parent: ViewGroup, viewType: Int, view: View): VH

    /**
     * @param holder
     * *
     * @param position 绑定数据
     */
    abstract fun bindData(holder: VH, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return when (viewType) {
            Const.VIEW_TYPE_HEADER -> {
                createVH(parent, viewType, header!!)
            }
            Const.VIEW_TYPE_FOOTER -> {
                createVH(parent, viewType, footer!!)
            }
            else -> {
                val view = LayoutInflater.from(context).inflate(provideItemLayoutId(), null)
                createVH(parent, viewType, view)
            }
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (holder.itemViewType != Const.VIEW_TYPE_HEADER && holder.itemViewType != Const.VIEW_TYPE_FOOTER) {
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener { view -> mOnItemClickListener!!.onItemClick(view, if (header == null) position else position - 1, data[if (header == null) position else position - 1]) }
            } else {
                holder.itemView.setOnClickListener(null)
            }
            bindData(holder, if (header == null) position else position - 1)
        }
    }

    override fun getItemCount(): Int {
        return if (header != null) {
            if (isAddFooter) data.size + 2 else data.size + 1
        } else {
            if (isAddFooter) data.size + 1 else data.size
        }
    }

    /**
     * 设置监听者
     */
    fun setOnItemClickListener(listener: OnItemClickListener<B>) {
        this.mOnItemClickListener = listener
    }


    /**
     * 先清除后添加

     * @param data
     */
    fun addDatas(data: ArrayList<B>) {
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }

    fun addLastData(b: B) {
        this.data.add(b)
        this.notifyItemChanged(data.size - 1)
    }

    fun addFirstData(b: B) {
        this.data.add(0, b)
        this.notifyItemInserted(0)
    }

    fun removeFirstData() {
        this.data.removeAt(0)
        this.notifyItemRemoved(0)
    }

    fun removeLastData() {
        this.data.removeAt(data.size - 1)
        this.notifyItemChanged(data.size - 1)
        notifyDataSetChanged()
    }

    /**
     * 先清除后添加(动画)
     * @param data
     */
    fun addDataAnim(data: ArrayList<B>) {
        this.data.clear()
        for (i in 0 until data.size) {
            this.data.add(data[i])
            this.notifyItemChanged(i)
        }
    }

    /**
     * 添加更多
     * @param data
     */
    fun addMore(data: ArrayList<B>) {
        this.data.addAll(data)
        this.notifyItemRangeChanged(this.data.size, data.size)
    }

    fun loading() {
        STATUS = Const.STATUS_LOADING
        if (tvStatus != null) {
            tvStatus!!.text = context.getString(R.string.listdata_status_loading)
        }
    }

    fun hasMore() {
        STATUS = Const.STATUS_HASMORE
        if (tvStatus != null) {
            tvStatus!!.text = context.getString(R.string.listdata_status_has_more)
        }
    }

    fun noMore() {
        STATUS = Const.STATUS_NOMORE
        if (tvStatus != null) {
            tvStatus!!.text = context.getString(R.string.listdata_status_no_more)
        }
    }

    fun loading(tip: String) {
        STATUS = Const.STATUS_LOADING
        if (tvStatus != null) {
            tvStatus!!.text = tip
        }
    }

    fun hasMore(tip: String) {
        STATUS = Const.STATUS_HASMORE
        if (tvStatus != null) {
            tvStatus!!.text = tip
        }
    }

    fun noMore(tip: String) {
        STATUS = Const.STATUS_NOMORE
        if (tvStatus != null) {
            tvStatus!!.text = tip
        }
    }

    fun getStatus(): Int = this.STATUS

    fun setFooter(isAddFooter: Boolean) {
        this.isAddFooter = isAddFooter
    }

    fun removeItem(b: B) {
        this.data.remove(b)
        this.notifyDataSetChanged()
    }
}