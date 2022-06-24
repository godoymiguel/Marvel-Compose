package com.godamy.marvelcompose.ui.screen.event.detail

import com.godamy.marvelcompose.data.entities.Event

data class EventDetailUiState(
    val loading: Boolean = false,
    val event: Event? = null
)
