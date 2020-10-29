package com.dj.kotlin.coroutines

import android.os.Bundle
import android.util.Log
import com.dj.kotlin.R
import com.dj.kotlin.base.BaseActivity
import com.dj.kotlin.databinding.ActivityCoroutinesBinding
import com.dj.library.LogUtils
import kotlinx.coroutines.*

/**
 * 协程
 */
class CoroutinesActivity : BaseActivity<ActivityCoroutinesBinding>(){
    private val TAG = CoroutinesActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.runBlocking.setOnClickListener{
            LogUtils.e(TAG, "主线程id：${mainLooper.thread.id}")
            runBlockingTest()
            LogUtils.e(TAG, "协程执行结束")
        }

        dataBinding.job.setOnClickListener{
            jobTest();
        }

        dataBinding.jobLaunch.setOnClickListener{
            jobLaunchTest();
        }

        dataBinding.async.setOnClickListener{
            asyncTest();
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_coroutines
    }

    /**
     * runBlocking启动的协程任务会阻断当前线程，直到该协程执行结束
     */
    private fun runBlockingTest() = runBlocking {
        repeat(8) {
            LogUtils.e(TAG, "协程执行$it 线程id：${Thread.currentThread().id}")
            delay(1000)
        }
    }

    private fun jobTest() {
        LogUtils.e(TAG, "主线程id：${mainLooper.thread.id}")
        val job = GlobalScope.launch {
            delay(2000)//延迟2秒
            LogUtils.e(TAG, "协程执行结束 -- 线程：${Thread.currentThread()}")
        }
        LogUtils.e(TAG, "主线程执行结束")
    }

    private fun jobLaunchTest() {
        GlobalScope.launch {
            val token = getToken()
            val userInfo = getUserInfo(token)
            setUserInfo(userInfo)
        }
        repeat(8){
            LogUtils.e(TAG,"主线程执行$it")
        }
    }
    private fun setUserInfo(userInfo: String) {
        LogUtils.e(TAG, userInfo)
    }

    /**
     * suspend函数会将整个协程挂起，而不仅仅是这个suspend函数，也就是说一个协程中有多个挂起函数时，它们是顺序执行的。
     */
    private suspend fun getToken(): String {
        delay(2000)
        return "token"
    }
    private suspend fun getUserInfo(token: String): String {
        delay(2000)
        return "$token - userInfo"
    }

    private fun asyncTest() {
        LogUtils.e(TAG,"start : ${System.currentTimeMillis()}")
        GlobalScope.launch {
            val result1 = GlobalScope.async {
                getResult1()
            }
            val result2 = GlobalScope.async {
                getResult2()
            }
            val result = result1.await() + result2.await()
            //async是不阻塞线程的,也就是说getResult1和getResult2是同时进行的，所以获取到result的时间是4s，而不是7s。
            LogUtils.e(TAG,"result = $result，${System.currentTimeMillis()}")
        }
    }
    private suspend fun getResult1(): Int {
        delay(3000)
        return 1
    }


    private suspend fun getResult2(): Int {
        delay(4000)
        return 2
    }
}