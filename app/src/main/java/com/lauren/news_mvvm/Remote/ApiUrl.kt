package com.lauren.news_mvvm.Remote

object ApiUrl {
    const val hostUrl = "https://newsapi.org/"
    const val apiKey = "ac95f70554bd4e079d2d79789a8d300d"
    const val searchNews = "v2/everything"
    const val topHeadlines = "v2/top-headlines?country=us&apiKey=$apiKey"
}