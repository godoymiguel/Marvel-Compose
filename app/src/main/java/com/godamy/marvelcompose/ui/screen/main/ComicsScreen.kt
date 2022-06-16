package com.godamy.marvelcompose.ui.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.ComicsRepository

@ExperimentalFoundationApi
@Composable
fun ComicsScreen(onClick: (Comic) -> Unit) {
    var comicsState by rememberSaveable {
        mutableStateOf(emptyList<Comic>())
    }

    LaunchedEffect(Unit) {
        comicsState = ComicsRepository.get()
    }

    Scaffold(
        topBar = {
            MainAppBar()
        }
    ) {
        ComicList(
            comics = comicsState,
            onClick = onClick
        )
    }
}
