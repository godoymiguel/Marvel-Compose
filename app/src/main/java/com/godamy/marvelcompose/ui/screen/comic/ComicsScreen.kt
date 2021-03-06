package com.godamy.marvelcompose.ui.screen.comic

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.ui.screen.main.ComicFormatTabRow
import com.godamy.marvelcompose.ui.screen.main.MarvelItemsVerticalGrid
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun ComicsScreen(onClick: (Comic) -> Unit, viewModel: ComicViewModel = hiltViewModel()) {

    val formats = Comic.Format.values().toList()
    val pagerState = rememberPagerState()

    Column {
        ComicFormatTabRow(pagerState, formats)
        HorizontalPager(
            count = formats.size,
            state = pagerState
        ) { page ->
            val format = formats[page]
            viewModel.formatRequested(format)
            val pageState by viewModel.state.getValue(format).collectAsState()

            MarvelItemsVerticalGrid(
                loading = pageState.loading,
                marvelItems = pageState.comics,
                onClick = onClick
            )
        }
    }
}
