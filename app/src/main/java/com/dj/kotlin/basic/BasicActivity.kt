package com.dj.kotlin.basic

import android.os.Bundle
import android.view.View
import com.dj.kotlin.R
import com.dj.kotlin.base.BaseActivity
import com.dj.kotlin.bean.User
import com.dj.kotlin.databinding.ActivityBasicBinding
import com.dj.kotlin.extensions.print
import com.dj.library.LogUtils

class BasicActivity : BaseActivity<ActivityBasicBinding>(),View.OnClickListener {
    private lateinit var user:User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = User()
        user.username = "axb"
        user.password = "123"

        dataBinding.letButton.setOnClickListener(this)
        dataBinding.withButton.setOnClickListener(this)
        dataBinding.applyButton.setOnClickListener(this)
        dataBinding.extension.setOnClickListener(this)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_basic
    }

    override fun onClick(v: View) {
        when(v.id){
            /**
             * let函数适用的场景
             * 场景一: 最常用的场景就是使用let函数处理需要针对一个可null的对象统一做判空处理。
             * 场景二: 然后就是需要去明确一个变量所处特定的作用域范围内可以使用
             */
            R.id.letButton -> {
                user?.let {
                    //在函数体内使用it替代object对象去访问其公有的属性和方法
                    LogUtils.e("用户名：${user.username}")
                    LogUtils.e("密码：${it.password}")
                }
            }


            /**
             * 适用于调用同一个类的多个方法时，可以省去类名重复，直接调用类的方法即可，经常用于Android中RecyclerView中onBinderViewHolder中，数据model的属性映射到UI上
             */
            R.id.withButton -> {
                //标准写法
//                var result = with(user,{
//                    LogUtils.e("用户名：${user.username}")
//                    1000
//                })
                //简写
//                var result = with(user){
//                    LogUtils.e("用户名：${user.username}")
//                    1000
//                }
//                //返回值为函数块的最后一行或指定return表达式。
//                LogUtils.e("with的结果：$result")

                for(user in getData()){
                    with(user){
                        LogUtils.e("with的结果：$username")
                    }
                }
            }



            R.id.applyButton -> {
                var result = user.apply {
                    LogUtils.e("用户名：${user.username}")
                    1000
                }
                //apply函数的返回的是传入对象的本身
                LogUtils.e("apply的结果：$result")
            }


            R.id.extension -> {
                user.print()
            }
        }
    }


    private fun getData() : ArrayList<User>{
        var users = ArrayList<User>()
        var user:User
        for(i in 1..100){
            user = User()
            user.username = i.toString();
            users.add(user)
        }
        return users
    }
}