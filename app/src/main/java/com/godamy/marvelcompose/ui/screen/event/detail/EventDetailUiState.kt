package com.godamy.marvelcompose.ui.screen.event.detail

import arrow.core.Either
import com.godamy.marvelcompose.data.entities.Event
import com.godamy.marvelcompose.data.entities.Result

data class EventDetailUiState(
    val loading: Boolean = false,
    val event: Result<Event?> = Either.Right(null)
)
