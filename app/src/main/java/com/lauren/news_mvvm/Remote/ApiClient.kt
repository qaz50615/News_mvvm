package com.lauren.news_mvvm.Remote

import com.lauren.news_mvvm.Remote.ApiUrl.hostUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val retrofit: Retrofit = createRetrofit()

    private fun createRetrofit ():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(hostUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T:Any> createService(_class:Class<T>):T {
        return retrofit.create(_class)
    }

}