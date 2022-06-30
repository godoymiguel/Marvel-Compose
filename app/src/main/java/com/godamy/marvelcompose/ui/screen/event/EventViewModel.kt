package com.godamy.marvelcompose.ui.screen.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godamy.marvelcompose.data.repositories.EventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(eventsRepository: EventsRepository) : ViewModel() {

    private val _state = MutableStateFlow(EventUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = EventUiState(loading = true)
            _state.value = EventUiState(items = eventsRepository.get())
        }
    }
}
