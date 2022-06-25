package com.godamy.marvelcompose.ui.screen.comic

import arrow.core.right
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.entities.Result

data class ComicUiState(
    val loading: Boolean = false,
    val comics: Result<List<Comic>> = emptyList<Comic>().right()
)
