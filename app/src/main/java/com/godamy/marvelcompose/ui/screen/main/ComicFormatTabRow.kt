package com.godamy.marvelcompose.ui.screen.main

import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.godamy.marvelcompose.data.entities.Comic
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun ComicFormatTabRow(
    pagerState: PagerState,
    formats: List<Comic.Format>
) {

    val coroutineScope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,
        indicator = {
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(
                    pagerState = pagerState,
                    tabPositions = it
                )
            )
        }
    ) {
        formats.forEach {
            Tab(
                selected = it.ordinal == pagerState.currentPage,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(it.ordinal)
                    }
                },
                text = { Text(text = it.key) }
            )
        }
    }
}