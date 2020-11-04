package com.dj.kotlin.coroutines

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

    override fun getLayoutRes(): Int {
        return R.layout.activity_coroutines
    }

    override fun initData() {
        dataBinding.runBlocking.setOnClickListener{
            LogUtils.e(TAG, "主线程id：${mainLooper.thread.id}")
            runBlockingTest()
            LogUtils.e(TAG, "协程执行结束")
        }

        dataBinding.job.setOnClickListener{
            jobTest()
        }

        dataBinding.jobLaunch.setOnClickListener{
            jobLaunchTest()
        }

        dataBinding.async.setOnClickListener{
            asyncTest()
        }

        dataBinding.suspend.setOnClickListener{
            suspendTest()
        }
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

    private fun suspendTest(){
        //CoroutineDispatcher，协程调度器，决定协程所在的线程或线程池。它可以指定协程运行于特定的一个线程、一个线程池或者不指定任何线程（这样协程就会运行于当前线程）。coroutines-core中 CoroutineDispatcher 有三种标准实现Dispatchers.Default、Dispatchers.IO，Dispatchers.Main和Dispatchers.Unconfined，Unconfined 就是不指定线程。
        //suspend为挂起函数，
//        GlobalScope.launch {
//            suspendFun1()
//            suspendFun2()
//        }

        GlobalScope.launch(Dispatchers.IO) {
            suspendFun1()
            suspendFun2()
        }
    }

    private suspend fun suspendFun1(){
        //suspend挂起函数挂起协程时，不会阻塞协程所在的线程
//        delay(3000)
        LogUtils.e("suspendFun1当前线程：${Thread.currentThread()}")
    }

    private fun suspendFun2(){
        LogUtils.e("suspendFun2当前线程：${Thread.currentThread()}")
    }
}