package com.dj.kotlin

import android.view.View
import com.dj.kotlin.base.BaseActivity
import com.dj.kotlin.basic.BasicActivity
import com.dj.kotlin.coroutines.CoroutinesActivity
import com.dj.kotlin.coroutines.RetrofitActivity
import com.dj.kotlin.databinding.ActivityMainBinding
import com.dj.kotlin.extensions.openActivity

class MainActivity : BaseActivity<ActivityMainBinding>(),View.OnClickListener {
    override fun initData() {
        dataBinding.coroutines.setOnClickListener(this)
        dataBinding.base.setOnClickListener(this)
        dataBinding.retrofit.setOnClickListener(this)
        dataBinding.changeColor.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        //kotlin中，when取代switch
        when(v.id){
            R.id.coroutines ->openActivity(CoroutinesActivity::class.java)
            R.id.base ->openActivity(BasicActivity::class.java)
            R.id.retrofit -> openActivity(RetrofitActivity::class.java)
            R.id.changeColor -> changeColor()
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }
}