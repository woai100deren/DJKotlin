package com.dj.kotlin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dj.kotlin.base.BaseActivity
import com.dj.kotlin.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.textView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        //kotlin中，when取代switch
        when(v.id){
            R.id.textView -> Toast.makeText(this,"123123",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }
}