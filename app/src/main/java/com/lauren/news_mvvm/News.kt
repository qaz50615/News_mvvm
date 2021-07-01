package com.lauren.news_mvvm

data class News(
        val articles: List<NewsArticle>,
        val status: String,
        val totalResults: Int
)