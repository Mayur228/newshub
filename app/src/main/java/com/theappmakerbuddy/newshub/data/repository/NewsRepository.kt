package com.theappmakerbuddy.newshub.data.repository

import com.khush.newshub.data.database.entity.Article
import com.khush.newshub.data.database.entity.Source
import com.theappmakerbuddy.newshub.common.Const
import com.theappmakerbuddy.newshub.common.Const.DEFAULT_PAGE_NUM
import com.theappmakerbuddy.newshub.common.util.apiArticleListToArticleList
import com.theappmakerbuddy.newshub.common.util.apiSourceListToSourceList
import com.theappmakerbuddy.newshub.data.database.DatabaseService
import com.theappmakerbuddy.newshub.data.model.Country
import com.theappmakerbuddy.newshub.data.model.Language
import com.theappmakerbuddy.newshub.data.network.ApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val database: DatabaseService,
    private val network: ApiInterface
) {

    suspend fun getNews(pageNumber: Int = DEFAULT_PAGE_NUM): List<Article> {
        val articles = network.getNews(
            pageNum = pageNumber
        ).articles.apiArticleListToArticleList()
        return if (pageNumber == DEFAULT_PAGE_NUM) {
            database.deleteAllAndInsertAll(articles)
            database.getAllArticles().first()
        } else {
            articles
        }
    }

    suspend fun getNewsFromDb(): List<Article> {
        return database.getAllArticles().first()
    }


    suspend fun getNewsByCountry(
        countryCode: String,
        pageNumber: Int = DEFAULT_PAGE_NUM
    ): Flow<List<Article>> = flow {
        emit(
            network.getNews(
                countryCode,
                pageNum = pageNumber
            ).articles.apiArticleListToArticleList()
        )
    }

    suspend fun getNewsByLanguage(
        languageCode: String,
        pageNumber: Int = DEFAULT_PAGE_NUM
    ): Flow<List<Article>> = flow {
        emit(
            network.getNewsByLang(
                languageCode,
                pageNum = pageNumber
            ).articles.apiArticleListToArticleList()
        )
    }

    suspend fun getNewsBySource(
        sourceCode: String,
        pageNumber: Int = DEFAULT_PAGE_NUM
    ): Flow<List<Article>> = flow {
        emit(
            network.getNewsBySource(
                sourceCode,
                pageNum = pageNumber
            ).articles.apiArticleListToArticleList()
        )
    }

    suspend fun searchNews(
        searchQuery: String,
        pageNumber: Int = DEFAULT_PAGE_NUM
    ): Flow<List<Article>> =
        flow {
            emit(
                network.searchNews(searchQuery, pageNumber).articles.apiArticleListToArticleList()
            )
        }

    suspend fun upsert(article: Article) {
        database.upsert(article)
    }

    fun getSavedNews(): Flow<List<Article>> = database.getSavedArticles()

    suspend fun deleteArticle(article: Article) = database.deleteArticle(article)

    suspend fun getSources(): Flow<List<Source>> = flow {
        emit(
            network.getSources().sources.apiSourceListToSourceList()
        )
    }

    suspend fun getCountries(): Flow<List<Country>> = flow {
        emit(Const.countryList)
    }

    suspend fun getLanguages(): Flow<List<Language>> = flow {
        emit(Const.languageList)
    }
}