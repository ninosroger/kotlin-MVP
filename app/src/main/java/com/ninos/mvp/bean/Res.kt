package com.ninos.mvp.bean

/**
 * Created by ninos on 2017/5/26.
 */
data class Res<out T>(val code: String, val message: String, val data: T)