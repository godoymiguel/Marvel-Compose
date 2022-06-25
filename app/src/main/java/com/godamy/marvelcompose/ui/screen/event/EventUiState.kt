package com.godamy.marvelcompose.ui.screen.event

import arrow.core.right
import com.godamy.marvelcompose.data.entities.Event
import com.godamy.marvelcompose.data.entities.Result

data class EventUiState(
    val loading: Boolean = false,
    val items: Result<List<Event>> = emptyList<Event>().right()
)
