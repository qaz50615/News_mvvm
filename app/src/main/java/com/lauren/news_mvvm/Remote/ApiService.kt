package com.lauren.news_mvvm.Remote

import com.lauren.news_mvvm.News
import com.lauren.news_mvvm.Remote.ApiUrl.searchNews
import com.lauren.news_mvvm.Remote.ApiUrl.topHeadlines
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET(topHeadlines)
    suspend fun getBreakingNews():News
    @POST(searchNews)
    suspend fun searchNews(@Query("q")q:String):News
}