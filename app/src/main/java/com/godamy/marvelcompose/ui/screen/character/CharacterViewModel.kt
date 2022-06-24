package com.godamy.marvelcompose.ui.screen.character

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godamy.marvelcompose.data.repositories.CharactersRepository
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    var state by mutableStateOf(CharacterUiState())
        private set

    init {
        viewModelScope.launch {
            state = CharacterUiState(loading = true)
            state = CharacterUiState(items = CharactersRepository.get())
        }
    }
}
