package com.dj.kotlin.coroutines.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.dj.kotlin.bean.WBean
import com.dj.kotlin.extensions.dataConvert
import com.dj.kotlin.network.ApiService
import com.dj.kotlin.network.Resource
import com.dj.kotlin.network.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NetViewModel : ViewModel() {
    //   vm持有数据层引用，并利用livedata赋值更新UI
    var list= MutableLiveData<List<WBean>>()

    /**
     * viewModelScope是一个绑定到当前viewModel的作用域  当ViewModel被清除时会自动取消该作用域，所以不用担心内存泄漏为问题
     */
    fun getTopArticle(){
        viewModelScope.launch {
            try {
                //withContext表示挂起块  配合Retrofit声明的suspend函数执行 该块会挂起直到里面的网络请求完成 最后一行就是返回值
                val data = withContext(Dispatchers.IO){
                    //dataConvert扩展函数可以很方便的解析出我们想要的数据  接口很多的情况下可以节省不少无用代码
                    RetrofitFactory.instance.getService(ApiService::class.java).getTopArticle().dataConvert()
                }
                //给LiveData赋值  ui会自动更新
                list.value = data
            }catch (e:Exception){
                e.printStackTrace()
                Log.e("net error","网络请求错误${e.toString()}")
            }
        }
    }

    fun getTopArticle2() = liveData(Dispatchers.IO) {
        emit(Resource.start())
        try {
            emit(Resource.success(RetrofitFactory.instance.getService(ApiService::class.java).getTopArticle().dataConvert()))
        } catch (e: Exception) {
            emit(Resource.error(e.message, null))
        } finally {
            emit(Resource.finish())
        }
    }
}