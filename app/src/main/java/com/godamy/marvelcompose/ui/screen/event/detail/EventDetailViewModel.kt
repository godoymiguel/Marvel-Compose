package com.godamy.marvelcompose.ui.screen.event.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godamy.marvelcompose.data.repositories.EventsRepository
import com.godamy.marvelcompose.ui.navigation.NavArg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EventDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _state = MutableStateFlow(EventDetailUiState())
    val state = _state.asStateFlow()

    private val eventId = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    init {
        viewModelScope.launch {
            _state.value = EventDetailUiState(loading = true)
            _state.value = EventDetailUiState(event = EventsRepository.find(eventId))
        }
    }
}
