package com.theappmakerbuddy.newshub.ui.screens

import android.graphics.drawable.Icon
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.theappmakerbuddy.newshub.data.database.entity.Article
import com.theappmakerbuddy.newshub.R
import com.theappmakerbuddy.newshub.common.NoInternetException
import com.theappmakerbuddy.newshub.common.util.filterArticles
import com.theappmakerbuddy.newshub.ui.base.ShowError
import com.theappmakerbuddy.newshub.ui.base.ShowLoading
import com.theappmakerbuddy.newshub.ui.base.UIState
import com.theappmakerbuddy.newshub.ui.components.NewsLayout
import com.theappmakerbuddy.newshub.ui.theme.LogoColorMain
import com.theappmakerbuddy.newshub.ui.viewmodels.SearchViewModel


@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    backPressed: () -> Unit,
    newsClicked: (Article) -> Unit
) {
    val searchUIState: UIState<List<Article>> by searchViewModel.searchNewsItem.collectAsStateWithLifecycle()
    val searchQuery: String by searchViewModel.query.collectAsStateWithLifecycle()
    SearchLayout(
        searchQuery = searchQuery,
        searchUIState = searchUIState,
        newsClicked = newsClicked,
        retrySearch = {
            searchViewModel.searchNews()
        },
        backPressed = backPressed
    ) {
        searchViewModel.searchNews(it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLayout(
    searchQuery: String,
    searchUIState: UIState<List<Article>>,
    newsClicked: (Article) -> Unit,
    retrySearch: () -> Unit,
    backPressed: () -> Unit,
    onSearchQueryChange: (String) -> Unit
) {

    SearchBar(
        query = searchQuery,
        onQueryChange = onSearchQueryChange,
        onSearch = {},
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        active = true,
        onActiveChange = {},
        tonalElevation = 0.dp,
        colors = SearchBarDefaults.colors(containerColor = LogoColorMain)
    ) {
        when (searchUIState) {
            is UIState.Loading -> {
                ShowLoading()
            }

            is UIState.Failure -> {
                var errorText = stringResource(id = R.string.something_went_wrong)
                if (searchUIState.throwable is NoInternetException) {
                    errorText = stringResource(id = R.string.no_internet_available)
                }
                ShowError(
                    text = errorText,
                    retryEnabled = true
                ) {
                    retrySearch()
                }
            }

            is UIState.Success -> {
                if (searchUIState.data.filterArticles().isEmpty()) {
                    ShowError(text = stringResource(id = R.string.no_data_available))
                } else {
                    NewsLayout(newsList = searchUIState.data.filterArticles()) {
                        newsClicked(it)
                    }
                }
            }

            is UIState.Empty -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = LogoColorMain),
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_ic),
                        contentDescription = null,
                        modifier = Modifier
                            .width(120.dp)
                            .height(120.dp)
                    )
                    Text(
                        text = "Search News here",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(15.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }

    BackHandler {
        backPressed()
    }
}
