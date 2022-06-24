package com.godamy.marvelcompose.ui.screen.character.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godamy.marvelcompose.data.repositories.CharactersRepository
import com.godamy.marvelcompose.ui.navigation.NavArg
import kotlinx.coroutines.launch

class CharacterDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    var state by mutableStateOf(CharacterDetailUiState())
        private set

    private val characterId = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    init {
        viewModelScope.launch {
            state = CharacterDetailUiState(loading = true)
            state = CharacterDetailUiState(character = CharactersRepository.find(characterId))
        }
    }
}
