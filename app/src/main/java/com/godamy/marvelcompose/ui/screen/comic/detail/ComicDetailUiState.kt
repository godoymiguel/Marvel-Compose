package com.godamy.marvelcompose.ui.screen.comic.detail

import arrow.core.Either
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.entities.Result

data class ComicDetailUiState(
    val loading: Boolean = false,
    val comic: Result<Comic?> = Either.Right(null)
)
