package com.theappmakerbuddy.newshub.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.theappmakerbuddy.newshub.data.database.entity.Article
import com.theappmakerbuddy.newshub.common.Const
import com.theappmakerbuddy.newshub.common.NoInternetException
import com.theappmakerbuddy.newshub.common.dispatcher.DispatcherProvider
import com.theappmakerbuddy.newshub.common.networkhelper.NetworkHelper
import com.theappmakerbuddy.newshub.data.repository.NewsRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsPagingSource @Inject constructor(
    private val newsRepository: NewsRepository,
    private val networkHelper: NetworkHelper,
    private val dispatcherProvider: DispatcherProvider
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        lateinit var loadResult: LoadResult<Int, Article>

        withContext(dispatcherProvider.io) {
            kotlin.runCatching {
                if (!networkHelper.isNetworkConnected()) {
                    if (page == Const.DEFAULT_PAGE_NUM) {
                        val articles = newsRepository.getNewsFromDb()
                        loadResult = LoadResult.Page(
                            data = articles,
                            prevKey = page.minus(1),
                            nextKey = if (articles.isEmpty()) null else page.plus(1)
                        )
                    } else {
                        throw NoInternetException()
                    }
                } else {
                    val articles = newsRepository.getNews(page)
                    loadResult = LoadResult.Page(
                        data = articles,
                        prevKey = if (page == 1) null else page.minus(1),
                        nextKey = if (articles.isEmpty()) null else page.plus(1)
                    )
                }
            }.onFailure {
                loadResult = LoadResult.Error(it)
            }
        }
        return loadResult
    }
}