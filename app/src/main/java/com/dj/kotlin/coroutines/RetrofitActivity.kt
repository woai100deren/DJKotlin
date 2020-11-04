package com.dj.kotlin.coroutines

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dj.kotlin.R
import com.dj.kotlin.base.BaseActivity
import com.dj.kotlin.coroutines.vm.NetViewModel
import com.dj.kotlin.databinding.ActivityRetrofitBinding
import com.dj.kotlin.network.Status
import com.dj.library.LogUtils
import com.google.gson.Gson

class RetrofitActivity : BaseActivity<ActivityRetrofitBinding>() {
    private lateinit var netViewModel:NetViewModel
    override fun getLayoutRes(): Int {
        return R.layout.activity_retrofit
    }

    override fun initData() {
        netViewModel = ViewModelProvider(this).get(NetViewModel::class.java)
        dataBinding.requestNetwork1.setOnClickListener {
            netViewModel.getTopArticle()
        }

        dataBinding.requestNetwork2.setOnClickListener {
            netViewModel.getTopArticle2().observe(this, Observer {
                it?.let {
                    resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            dataBinding.textView.text = Gson().toJson(it.data)
                        }
                        Status.ERROR -> {
                            dataBinding.textView.text = "请求网络失败：${resource.message}"
                        }
                        Status.START -> {
                            LogUtils.e("开始请求数据")
                        }
                        Status.FINISH -> {
                            LogUtils.e("网络请求结束")
                        }
                    }
                }
            })
        }

        netViewModel.list.observe(this, Observer {
            dataBinding.textView.text = Gson().toJson(it)
        })
    }
}