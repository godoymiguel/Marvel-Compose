package com.godamy.marvelcompose.ui.screen.event

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.ComicsRepository
import com.godamy.marvelcompose.ui.screen.main.MarvelItemsList

@ExperimentalFoundationApi
@Composable
fun EventsScreen(onClick: (Comic) -> Unit) {
    var eventsState by rememberSaveable {
        mutableStateOf(emptyList<Comic>())
    }

    LaunchedEffect(Unit) {
        eventsState = ComicsRepository.get()
    }

    MarvelItemsList(eventsState, onClick)
}
