package com.godamy.marvelcompose.ui.screen.comic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.ComicsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ComicViewModel : ViewModel() {

    val state = Comic.Format.values().associateWith { MutableStateFlow(ComicUiState()) }

    fun formatRequested(format: Comic.Format) {
        val uiState = state.getValue(format)

        val comic = uiState.value.comics

        if (comic is Either.Right && comic.value.isEmpty()) {
            viewModelScope.launch {
                uiState.value = ComicUiState(loading = true)
                uiState.value = ComicUiState(comics = ComicsRepository.get(format))
            }
        }
    }
}
