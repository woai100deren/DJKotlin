package com.dj.kotlin.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.dj.kotlin.bean.User
import com.dj.kotlin.network.BaseResp
import com.dj.library.LogUtils

fun User.print(){
    LogUtils.e(this.username+","+this.password)
}

fun <T> BaseResp<T>.dataConvert(): T {
    if (errorCode == 0) {
        return data
    } else {
        throw Exception(errorMsg)
    }
}

/**
 * 全局toast
 */
fun Context.toast(msg: String) {
    Toast.makeText(this, msg,  LENGTH_SHORT).show()
}

/**
 * 全局跳转
 */
fun Activity.openActivity(cls: Class<*>) {
    startActivity(Intent(this, cls))
}