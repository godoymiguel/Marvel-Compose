package com.godamy.marvelcompose.ui.screen.detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.ComicsRepository
import com.godamy.marvelcompose.ui.screen.common.ArrowBack

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
        Scaffold(
            topBar = {
                DetailAppBar(comic, onBackClick)
            }
        ) {
            ComicDetail(comic = comic)
        }
    }
}
