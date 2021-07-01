package com.lauren.news_mvvm.ui.breakingNews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lauren.news_mvvm.NewsArticle
import com.lauren.news_mvvm.NewsState
import com.lauren.news_mvvm.Remote.ApiClient
import com.lauren.news_mvvm.Remote.ApiService
import com.lauren.news_mvvm.Remote.ApiUrl.apiKey
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {

    val newsState = MutableLiveData<NewsState>()
    var oriData: NewsState? = null

    private suspend fun getNewsDataResponse (): NewsState {
        return try {
            val result = ApiClient().createService(ApiService::class.java).getBreakingNews()
            NewsState.Succeed(result.articles)
        } catch (e:Exception) {
            NewsState.Error(e)
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            val state = getNewsDataResponse()
            newsState.value = state
            oriData = state
        }
    }

    fun filterData(query:String) {
        val filterList = (oriData as? NewsState.Succeed)?.news?.filter { it.title?.contains(query)?:false || it.content?.contains(query)?:false }?: emptyList()
        newsState.value = NewsState.Succeed(filterList)
    }

}