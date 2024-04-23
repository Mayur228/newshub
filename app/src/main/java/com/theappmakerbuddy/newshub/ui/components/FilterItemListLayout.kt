package com.theappmakerbuddy.newshub.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.khush.newshub.data.database.entity.Source
import com.theappmakerbuddy.newshub.data.model.Country
import com.theappmakerbuddy.newshub.data.model.Language

@Composable
fun CountryListLayout(
    countryList: List<Country>,
    countryClicked: (Country) -> Unit
) {
    LazyColumn {
        items(countryList) {
            CountryItem(it) { country ->
                countryClicked(country)
            }
        }
    }
}

@Composable
fun LanguageListLayout(
    languageList: List<Language>,
    languageClicked: (Language) -> Unit
) {
    LazyColumn {
        items(languageList) {
            LanguageItem(it) { language ->
                languageClicked(language)
            }
        }
    }
}

@Composable
fun SourceListLayout(
    sourceList: List<Source>,
    sourceClicked: (Source) -> Unit
) {
    LazyColumn {
        items(sourceList) {
            SourceItem(it) { source ->
                sourceClicked(source)
            }
        }
    }
}