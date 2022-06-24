package com.godamy.marvelcompose.ui.screen.comic

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.ComicsRepository
import kotlinx.coroutines.launch

class ComicViewModel : ViewModel() {


    private val _state = Comic.Format.values().associateWith { mutableStateOf(ComicUiState()) }
    val state get() = _state

    fun formatRequested(format: Comic.Format) {
        val uiState = _state.getValue(format)

        if (uiState.value.items.isNotEmpty()) return

        viewModelScope.launch {
            uiState.value = ComicUiState(loading = true)
            uiState.value = ComicUiState(items = ComicsRepository.get(format))
        }
    }
}