package com.godamy.marvelcompose.ui.screen.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godamy.marvelcompose.data.repositories.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(charactersRepository: CharactersRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(CharacterUiState())
    val state: StateFlow<CharacterUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = CharacterUiState(loading = true)
            _state.value = CharacterUiState(items = charactersRepository.get())
        }
    }
}
