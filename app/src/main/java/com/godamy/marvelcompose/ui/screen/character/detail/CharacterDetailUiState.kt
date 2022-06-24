package com.godamy.marvelcompose.ui.screen.character.detail

import com.godamy.marvelcompose.data.entities.Character

data class CharacterDetailUiState(
    val loading: Boolean = false,
    val character: Character? = null
)
