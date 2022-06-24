package com.godamy.marvelcompose.ui.screen.event

import com.godamy.marvelcompose.data.entities.Event

data class EventUiState(
    val loading: Boolean = false,
    val items: List<Event> = emptyList()
)
