package com.godamy.marvelcompose.ui.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.godamy.marvelcompose.data.entities.MarvelItem

@ExperimentalFoundationApi
@Composable
fun <T : MarvelItem> MarvelItemsList(items: List<T>, onClick: (T) -> Unit) {
    Scaffold(
        topBar = {
            MainAppBar()
        }
    ) { padding ->
        LazyVerticalGrid(
            cells = GridCells.Adaptive(180.dp),
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier.padding(padding)
        ) {
            items(items) {
                MarvelItem(
                    marvelItem = it,
                    modifier = Modifier.clickable { onClick(it) }
                )
            }
        }
    }
}