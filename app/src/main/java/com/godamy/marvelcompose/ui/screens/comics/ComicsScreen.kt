package com.godamy.marvelcompose.ui.screens.comics

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.ComicsRepository

@ExperimentalFoundationApi
@Composable
fun ComicsScreen(onClick: (Comic) -> Unit) {
    var comicsState by rememberSaveable {
        mutableStateOf(emptyList<Comic>())
    }

    LaunchedEffect(Unit) {
        comicsState = ComicsRepository.getComics()
    }

    ComicsScreen(
        comics = comicsState,
        onClick = onClick
    )
}

@ExperimentalFoundationApi
@Composable
fun ComicsScreen(comics: List<Comic>, onClick: (Comic) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(comics) {
            ComicItem(
                comic = it,
                modifier = Modifier.clickable { onClick(it) }
            )
        }
    }
}

@Composable
fun ComicItem(
    comic: Comic,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(6.dp)
    ) {
        Card {
            Image(
                painter = rememberAsyncImagePainter(model = comic.thumbnail),
                contentDescription = comic.title,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxSize()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = comic.title,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            modifier = Modifier.padding(8.dp, 16.dp)
        )
    }
}
