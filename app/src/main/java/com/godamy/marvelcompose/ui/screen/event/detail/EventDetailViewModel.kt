package com.godamy.marvelcompose.ui.screen.event.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godamy.marvelcompose.data.repositories.EventsRepository
import com.godamy.marvelcompose.ui.navigation.NavArg
import kotlinx.coroutines.launch

class EventDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    var state by mutableStateOf(EventDetailUiState())
        private set

    private val eventId = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    init {
        viewModelScope.launch {
            state = EventDetailUiState(loading = true)
            state = EventDetailUiState(event = EventsRepository.find(eventId))
        }
    }
}
