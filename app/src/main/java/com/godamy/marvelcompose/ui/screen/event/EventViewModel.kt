package com.godamy.marvelcompose.ui.screen.event

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godamy.marvelcompose.data.repositories.EventsRepository
import com.godamy.marvelcompose.ui.navigation.NavArg
import kotlinx.coroutines.launch

class EventViewModel : ViewModel() {

    var state by mutableStateOf(EventUiState())
        private set

    init {
        viewModelScope.launch {
            state = EventUiState(loading = true)
            state = EventUiState(items = EventsRepository.get())
        }
    }
}
