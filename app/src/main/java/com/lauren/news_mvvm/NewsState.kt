package com.lauren.news_mvvm

import java.lang.Exception

sealed class NewsState {
    class Succeed(val news: List<NewsArticle>): NewsState()
    class Error(val error: Exception): NewsState()
    object Loading: NewsState()
}