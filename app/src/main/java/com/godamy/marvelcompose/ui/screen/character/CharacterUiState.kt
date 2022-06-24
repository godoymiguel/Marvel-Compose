package com.godamy.marvelcompose.ui.screen.character

import com.godamy.marvelcompose.data.entities.Character

data class CharacterUiState(
    val loading: Boolean = false,
    val items: List<Character> = emptyList()
)
