package com.godamy.marvelcompose.ui.screen.event

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.entities.Event
import com.godamy.marvelcompose.data.repositories.ComicsRepository
import com.godamy.marvelcompose.data.repositories.EventsRepository
import com.godamy.marvelcompose.ui.screen.detail.MarvelItemDetail

@ExperimentalMaterialApi
@Composable
fun EventDetailScreen(onBackClick: () -> Unit, itemId: Int) {
    var eventState by remember {
        mutableStateOf<Event?>(null)
    }
    LaunchedEffect(Unit) {
        eventState = EventsRepository.find(itemId)

    }
    eventState?.let { MarvelItemDetail(it, onBackClick) }
}
