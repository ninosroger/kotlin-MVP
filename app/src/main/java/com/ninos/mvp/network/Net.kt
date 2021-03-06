package com.ninos.mvp.network

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient

/**
 * Created by ninos on 2017/5/26.
 * 线程安全的懒加载单例模式
 */
class Net {
    companion object {
        fun getService(): ApiService = Holder.retrofit.create(ApiService::class.java)
    }

    private object Holder {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }
}