package com.dj.kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.dj.kotlin.base.BaseActivity
import com.dj.kotlin.basic.BasicActivity
import com.dj.kotlin.coroutines.CoroutinesActivity
import com.dj.kotlin.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.coroutines.setOnClickListener(this)
        dataBinding.base.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val int = Intent()
        //kotlin中，when取代switch
        when(v.id){
            R.id.coroutines ->int.setClass(this@MainActivity,CoroutinesActivity::class.java)//可以简写成：int.setClass(this,CoroutinesActivity::class.java)
            R.id.base ->int.setClass(this,BasicActivity::class.java)
        }
        startActivity(int)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }
}