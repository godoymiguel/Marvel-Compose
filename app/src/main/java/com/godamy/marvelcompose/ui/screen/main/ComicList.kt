package com.godamy.marvelcompose.ui.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.godamy.marvelcompose.data.entities.Comic

@ExperimentalFoundationApi
@Composable
fun ComicList(comics: List<Comic>, onClick: (Comic) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(comics) {
            ComicItem(
                comic = it,
                modifier = Modifier.clickable { onClick(it) }
            )
        }
    }
}