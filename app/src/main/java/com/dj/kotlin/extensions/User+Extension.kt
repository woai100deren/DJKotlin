package com.dj.kotlin.extensions

import com.dj.kotlin.bean.User
import com.dj.library.LogUtils

fun User.print(){
    LogUtils.e(this.username+","+this.password)
}