package com.godamy.marvelcompose.ui.screen.comic.detail


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godamy.marvelcompose.data.repositories.ComicsRepository
import com.godamy.marvelcompose.ui.navigation.NavArg
import kotlinx.coroutines.launch

class ComicDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    var state by mutableStateOf(ComicDetailUiState())
        private set

    private val comicId = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    init {
        viewModelScope.launch {
            state = ComicDetailUiState(loading = true)
            state = ComicDetailUiState(comic = ComicsRepository.find(comicId))
        }
    }
}
