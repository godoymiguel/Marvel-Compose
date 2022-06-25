package com.godamy.marvelcompose.ui.screen.character.detail

import arrow.core.Either
import com.godamy.marvelcompose.data.entities.Character
import com.godamy.marvelcompose.data.entities.Result

data class CharacterDetailUiState(
    val loading: Boolean = false,
    val character: Result<Character?> = Either.Right(null)
)
