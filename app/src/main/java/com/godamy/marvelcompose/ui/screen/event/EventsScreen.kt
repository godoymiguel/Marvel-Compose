package com.godamy.marvelcompose.ui.screen.event

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.entities.Event
import com.godamy.marvelcompose.data.repositories.ComicsRepository
import com.godamy.marvelcompose.data.repositories.EventsRepository
import com.godamy.marvelcompose.ui.screen.main.MarvelItemsList

@ExperimentalFoundationApi
@Composable
fun EventsScreen(onClick: (Event) -> Unit) {
    var eventsState by rememberSaveable {
        mutableStateOf(emptyList<Event>())
    }

    LaunchedEffect(Unit) {
        eventsState = EventsRepository.get()
    }

    MarvelItemsList(eventsState, onClick)
}
