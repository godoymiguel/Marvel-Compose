package com.godamy.marvelcompose.ui.screen.comic.detail

import com.godamy.marvelcompose.data.entities.Comic

data class ComicDetailUiState(
    val loading: Boolean = false,
    val comic: Comic? = null
)
