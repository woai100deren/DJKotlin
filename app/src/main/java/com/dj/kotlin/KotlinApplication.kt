package com.dj.kotlin

import android.app.Application
import com.dj.library.LogUtils

class KotlinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LogUtils.debug(BuildConfig.DEBUG)
    }
}