package com.godamy.marvelcompose.ui.screen.detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.ComicsRepository

@ExperimentalMaterialApi
@Composable
fun ComicDetailScreen(onBackClick: () -> Unit, comicId: Int) {
    var comicState by remember {
        mutableStateOf<Comic?>(null)
    }
    LaunchedEffect(Unit) {
        comicState = ComicsRepository.findComic(comicId)

    }
    comicState?.let { comic ->
        ComicDetail(comic = comic, onBackClick)
    }
}
