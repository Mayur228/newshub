package com.theappmakerbuddy.newshub.common.util

import com.khush.newshub.data.database.entity.Article

fun List<Article>.filterArticles(): List<Article> {
    return this.filterNot { article ->
        article.title.isNullOrEmpty() || article.description.isNullOrEmpty() || article.urlToImage.isNullOrEmpty()
    }
}