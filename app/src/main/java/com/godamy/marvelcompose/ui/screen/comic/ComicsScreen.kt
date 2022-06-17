package com.godamy.marvelcompose.ui.screen.comic

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.ComicsRepository
import com.godamy.marvelcompose.ui.screen.main.ComicFormatTabRow
import com.godamy.marvelcompose.ui.screen.main.MarvelItemsVerticalGrid
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun ComicsScreen(onClick: (Comic) -> Unit) {
    var comicsState by rememberSaveable { mutableStateOf(emptyList<Comic>()) }
    LaunchedEffect(Unit) {
        comicsState = ComicsRepository.get()
    }

    val formats = Comic.Format.values().toList()
    val pagerState = rememberPagerState()

    Column {
        ComicFormatTabRow(pagerState, formats)
        HorizontalPager(
            count = formats.size,
            state = pagerState
        ) {
            MarvelItemsVerticalGrid(
                items = comicsState,
                onClick = onClick
            )
        }
    }
}
