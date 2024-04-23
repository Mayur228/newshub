package com.theappmakerbuddy.newshub.ui.base

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.theappmakerbuddy.newshub.R

sealed class Route(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val icon: Int,
    val routeWithoutArgs: String = route
) {
    object TopNews :
        Route("topNews/{country}/{language}/{source}", R.string.news, R.drawable.news_ic, "topNews")

    object FilterNews : Route("filterNews", R.string.filter, R.drawable.filter_ic)
    object SavedNews : Route("savedNews", R.string.saved, R.drawable.save_ic)
    object SearchNews : Route("searchNews", R.string.search, R.drawable.search_ic)
    object NewsArticle :
        Route("newsArticle/{article}", R.string.news, R.drawable.news_ic, "newsArticle")
}

sealed class FilterRoute(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val icon: Int
) {
    object Country : FilterRoute("countryFilter", R.string.country, R.drawable.country_ic)
    object Language : FilterRoute("languageFilter", R.string.language, R.drawable.language_ic)
    object Source : FilterRoute("sourceFilter", R.string.source, R.drawable.source_ic)
}

val bottomBarScreens = listOf(
    Route.TopNews,
    Route.FilterNews,
    Route.SavedNews,
    Route.SearchNews
)

val filterScreens = listOf(
    FilterRoute.Country,
    FilterRoute.Language,
    FilterRoute.Source
)