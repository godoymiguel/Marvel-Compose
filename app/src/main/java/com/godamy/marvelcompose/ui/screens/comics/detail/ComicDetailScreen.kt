package com.godamy.marvelcompose.ui.screens.comics.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.ComicsRepository

@Composable
fun ComicDetailScreen(comicId: Int) {
    var comicState by remember {
        mutableStateOf<Comic?>(null)
    }
    LaunchedEffect(Unit) {
        comicState = ComicsRepository.findComic(comicId)

    }
    comicState?.let {
        ComicDetailScreen(comic = it)
    }
}

@Composable
fun ComicDetailScreen(comic: Comic) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = comic.title)
    }

}