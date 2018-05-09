package com.ninos.mvp.network

import com.ninos.mvp.bean.Res
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * Created by ninos on 2017/5/26.
 */
interface ApiService {
    @FormUrlEncoded
    @POST("demo/index.php?m=api&c=User&a=login")
    fun Login(@Field("mob") mob: String, @Field("pass") pass: String): Observable<Res<Any>>
}
