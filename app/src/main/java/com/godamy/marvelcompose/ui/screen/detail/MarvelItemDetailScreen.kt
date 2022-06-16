package com.godamy.marvelcompose.ui.screen.detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.ComicsRepository

@ExperimentalMaterialApi
@Composable
fun MarvelItemDetailScreen(onBackClick: () -> Unit, itemId: Int) {
    var marvelItemState by remember {
        mutableStateOf<Comic?>(null)
    }
    LaunchedEffect(Unit) {
        marvelItemState = ComicsRepository.find(itemId)

    }
    marvelItemState?.let { marvelItem ->
        MarvelItemDetail(marvelItem = marvelItem, onBackClick)
    }
}
