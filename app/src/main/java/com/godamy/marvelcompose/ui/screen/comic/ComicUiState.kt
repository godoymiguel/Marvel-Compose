package com.godamy.marvelcompose.ui.screen.comic

import com.godamy.marvelcompose.data.entities.Comic

data class ComicUiState(
    val loading: Boolean = false,
    val items: List<Comic> = emptyList()
)
