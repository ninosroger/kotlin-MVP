package com.ninos.mvp.adapter

import android.content.Context
import android.support.annotation.Nullable
import com.ninos.mvp.adapter.base.BaseAdapter
import com.ninos.mvp.presenter.RecyclerViewDemoPresenter
import butterknife.ButterKnife
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.ninos.mvp.R
import com.ninos.mvp.bean.DataItem


/**
 * Created by ninos on 2017/6/14.
 */
class RecyclerViewDemoAdapter(context: Context) : BaseAdapter<RecyclerViewDemoAdapter.VHolder, DataItem, RecyclerViewDemoPresenter>(context, false) {
    val SPAN_COUNT_ONE = 1
    val SPAN_COUNT_TWO = 2

    var span = 1

    override fun provideItemLayoutId() = R.layout.recycler_view_demo_item

    override fun createVH(parent: ViewGroup, viewType: Int, view: View): VHolder = VHolder(view)

    override fun bindData(holder: VHolder, position: Int) {
        val dataItem = data[position]
        if (span == SPAN_COUNT_ONE) {
            holder.cvGridItem.visibility = View.GONE
            holder.llLinearItem.visibility = View.VISIBLE
            holder.llTvTitle.text = dataItem.title
            holder.llTvUrl.text = dataItem.url
            holder.llTvContent.text = dataItem.content
            Glide.with(context)
                    .load(dataItem.icon)
                    .into(holder.llImgIcon)
        } else {
            holder.cvGridItem.visibility = View.VISIBLE
            holder.llLinearItem.visibility = View.GONE
            holder.cvTvTitle.text = dataItem.title
            holder.cvTvUrl.text = dataItem.url
            holder.cvTvContent.text = dataItem.content
            Glide.with(context)
                    .load(dataItem.icon)
                    .into(holder.cvImgIcon)
        }
    }

    inner class VHolder(view: View) : RecyclerView.ViewHolder(view) {
        @Nullable
        @BindView(R.id.cv_grid_item) lateinit var cvGridItem: CardView
        @Nullable
        @BindView(R.id.cv_tv_title) lateinit var cvTvTitle: TextView
        @Nullable
        @BindView(R.id.cv_tv_url) lateinit var cvTvUrl: TextView
        @Nullable
        @BindView(R.id.cv_tv_content) lateinit var cvTvContent: TextView
        @Nullable
        @BindView(R.id.cv_img_icon) lateinit var cvImgIcon: ImageView
        @Nullable
        @BindView(R.id.ll_linear_item) lateinit var llLinearItem: LinearLayout
        @Nullable
        @BindView(R.id.ll_tv_title) lateinit var llTvTitle: TextView
        @Nullable
        @BindView(R.id.ll_tv_url) lateinit var llTvUrl: TextView
        @Nullable
        @BindView(R.id.ll_tv_content) lateinit var llTvContent: TextView
        @Nullable
        @BindView(R.id.ll_img_icon) lateinit var llImgIcon: ImageView

        init {
            ButterKnife.bind(this, view)
        }
    }
}