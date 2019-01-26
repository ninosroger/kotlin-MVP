package com.ninos.mvp.ui.activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.ninos.mvp.R
import com.ninos.mvp.bean.DataItem
import com.ninos.mvp.presenter.ItemContentPresenter
import com.ninos.mvp.ui.activity.base.ToolBarActivity

/**
 * Created by ninos on 18-5-8.
 */
class ItemContentActivity : ToolBarActivity<ItemContentPresenter>() {
    lateinit var dataItem: DataItem
    @BindView(R.id.title) lateinit var title: TextView
    @BindView(R.id.tv_url) lateinit var url: TextView
    @BindView(R.id.tv_content) lateinit var content: TextView
    @BindView(R.id.img_icon) lateinit var icon: ImageView

    override fun initThings(savedInstanceState: Bundle?) {
        super.initThings(savedInstanceState)
        dataItem = intent.getBundleExtra("data").getSerializable("itemData") as DataItem
        title.text = dataItem.title
        tvTitle.text = dataItem.title
        url.text = dataItem.url
        content.text = dataItem.content
        Glide.with(getContext())
                .load(dataItem.icon)
                .into(icon)
    }

    override fun provideTitle(): CharSequence = "Content"

    override fun provideLayoutId(): Int = R.layout.activity_item_content

    override fun initListeners() {
    }

    override fun createPresenter(): ItemContentPresenter = ItemContentPresenter()
}