package com.godamy.marvelcompose.ui.screen.comic.detail


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godamy.marvelcompose.data.repositories.ComicsRepository
import com.godamy.marvelcompose.ui.navigation.NavArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    comicsRepository: ComicsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ComicDetailUiState())
    val state = _state.asStateFlow()

    private val comicId = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    init {
        viewModelScope.launch {
            _state.value = ComicDetailUiState(loading = true)
            _state.value = ComicDetailUiState(comic = comicsRepository.find(comicId))
        }
    }
}
