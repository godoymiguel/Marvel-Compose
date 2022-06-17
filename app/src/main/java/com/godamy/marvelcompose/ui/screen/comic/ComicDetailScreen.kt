package com.godamy.marvelcompose.ui.screen.comic

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.ComicsRepository
import com.godamy.marvelcompose.ui.screen.detail.MarvelItemDetail

@ExperimentalMaterialApi
@Composable
fun ComicDetailScreen(onBackClick: () -> Unit, itemId: Int) {
    var comicState by remember {
        mutableStateOf<Comic?>(null)
    }
    LaunchedEffect(Unit) {
        comicState = ComicsRepository.find(itemId)

    }
    comicState?.let { MarvelItemDetail(it, onBackClick) }
}
