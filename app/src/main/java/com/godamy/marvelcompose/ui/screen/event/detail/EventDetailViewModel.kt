package com.godamy.marvelcompose.ui.screen.event.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godamy.marvelcompose.data.repositories.EventsRepository
import com.godamy.marvelcompose.ui.navigation.NavArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    eventsRepository: EventsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(EventDetailUiState())
    val state = _state.asStateFlow()

    private val eventId = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    init {
        viewModelScope.launch {
            _state.value = EventDetailUiState(loading = true)
            _state.value = EventDetailUiState(event = eventsRepository.find(eventId))
        }
    }
}
