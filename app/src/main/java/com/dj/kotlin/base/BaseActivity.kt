package com.dj.kotlin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * 基础activity
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(){
    protected lateinit var dataBinding : T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,getLayoutRes())
    }

    abstract fun getLayoutRes(): Int
}