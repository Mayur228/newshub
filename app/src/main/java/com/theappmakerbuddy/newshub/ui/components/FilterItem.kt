package com.theappmakerbuddy.newshub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.theappmakerbuddy.newshub.data.database.entity.Source
import com.theappmakerbuddy.newshub.R
import com.theappmakerbuddy.newshub.data.model.Country
import com.theappmakerbuddy.newshub.data.model.Language
import com.theappmakerbuddy.newshub.ui.theme.LogoColor2
import com.theappmakerbuddy.newshub.ui.theme.LogoColorMain

@Composable
fun CountryItem(
    country: Country,
    onItemClick: (Country) -> Unit
) {
    /*Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(4.dp)
        .clickable {
            onItemClick(country)
        },
        colors = CardDefaults.cardColors(
            containerColor = CardBg
        )
    ) {

    }*/
    Column(
        modifier = Modifier
            .background(LogoColorMain)
            .fillMaxWidth()
            .height(40.dp)
            .clickable {
                onItemClick(country)
            },
    ) {
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = country.name, color = Color.White, modifier = Modifier.padding(horizontal = 8.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = LogoColor2, thickness = 2.dp)
    }

}

@Composable
fun LanguageItem(
    language: Language,
    onItemClick: (Language) -> Unit
) {
    Column(
        modifier = Modifier
            .background(LogoColorMain)
            .fillMaxWidth()
            .height(40.dp)
            .clickable {
                onItemClick(language)
            },
    ) {
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = language.name, color = Color.White, modifier = Modifier.padding(horizontal = 8.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = LogoColor2, thickness = 2.dp)
    }
}

@Composable
fun SourceItem(
    source: Source,
    onItemClick: (Source) -> Unit
) {
    Column(
        modifier = Modifier
            .background(LogoColorMain)
            .fillMaxWidth()
            .height(40.dp)
            .clickable {
                onItemClick(source)
            },
    ) {
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = source.name ?: stringResource(R.string.unknown), color = Color.White, modifier = Modifier.padding(horizontal = 8.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = LogoColor2, thickness = 2.dp)
    }

}
