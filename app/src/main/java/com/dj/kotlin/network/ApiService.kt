package com.dj.kotlin.network

import com.dj.kotlin.bean.WBean
import retrofit2.http.GET

interface ApiService {
    /**
     * 使用协程进行网络请求
     */
    @GET("article/top/json/")
    suspend fun getTopArticle(): BaseResp<List<WBean>>


//    @GET("article/list/{page}/json")
//    suspend fun getArticleList(@Path("page") page: Int = 0): Result<PageEntity<Article>>
}