package com.godamy.marvelcompose.ui.screen.character

import arrow.core.right
import com.godamy.marvelcompose.data.entities.Character
import com.godamy.marvelcompose.data.entities.Result

data class CharacterUiState(
    val loading: Boolean = false,
    val items: Result<List<Character>> = emptyList<Character>().right()
)
