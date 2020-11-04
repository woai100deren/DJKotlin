package com.dj.kotlin.base

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * 基础activity
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(){
    private var isGray = false;
    protected lateinit var dataBinding : T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,getLayoutRes())

        initData()
    }

    abstract fun getLayoutRes(): Int

    abstract fun initData()

    protected fun changeColor(){
        val paint = Paint()
        val colorMatrix = ColorMatrix()
        if(isGray) {
            colorMatrix.setSaturation(1f)//0是灰色的，1是原图
        }else{
            colorMatrix.setSaturation(0f)//0是灰色的，1是原图
        }
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        window.decorView.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
        isGray = !isGray
    }
}