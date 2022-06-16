package com.godamy.marvelcompose.ui.screen.comic

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.ComicsRepository
import com.godamy.marvelcompose.ui.screen.main.MarvelItemsList

@ExperimentalFoundationApi
@Composable
fun ComicsScreen(onClick: (Comic) -> Unit) {
    var comicsState by rememberSaveable {
        mutableStateOf(emptyList<Comic>())
    }

    LaunchedEffect(Unit) {
        comicsState = ComicsRepository.get()
    }

    MarvelItemsList(comicsState, onClick)
}
