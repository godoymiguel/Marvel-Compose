package com.godamy.marvelcompose.ui.screen.character.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godamy.marvelcompose.data.repositories.CharactersRepository
import com.godamy.marvelcompose.ui.navigation.NavArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    charactersRepository: CharactersRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailUiState())
    val state: StateFlow<CharacterDetailUiState> = _state.asStateFlow()

    private val characterId = savedStateHandle.get<Int>(NavArg.ItemId.key) ?: 0

    init {
        viewModelScope.launch {
            _state.value = CharacterDetailUiState(loading = true)
            _state.value =
                CharacterDetailUiState(character = charactersRepository.find(characterId))
        }
    }
}
