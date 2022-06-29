package com.godamy.marvelcompose.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.godamy.marvelcompose.R
import com.godamy.marvelcompose.data.entities.MarvelItem

@Composable
fun <T : MarvelItem> MarvelItem(
    marvelItem: T,
    modifier: Modifier = Modifier,
    onItemMoreClick: (T) -> Unit
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
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = marvelItem.title,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                modifier = Modifier
                    .padding(8.dp, 16.dp)
                    .weight(1f)
            )
            IconButton(onClick = { onItemMoreClick(marvelItem) }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(id = R.string.more_action)
                )
            }
        }
    }
}