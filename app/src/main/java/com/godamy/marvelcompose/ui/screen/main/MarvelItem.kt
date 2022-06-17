package com.godamy.marvelcompose.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.godamy.marvelcompose.data.entities.MarvelItem

@Composable
fun MarvelItem(
    marvelItem: MarvelItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(6.dp)
    ) {
        Card {
            Image(
                painter = rememberAsyncImagePainter(model = marvelItem.thumbnail),
                contentDescription = marvelItem.title,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxSize()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = marvelItem.title,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            modifier = Modifier.padding(8.dp, 16.dp)
        )
    }
}