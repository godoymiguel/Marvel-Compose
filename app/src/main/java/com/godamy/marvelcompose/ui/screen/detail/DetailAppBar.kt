package com.godamy.marvelcompose.ui.screen.detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.godamy.marvelcompose.data.entities.MarvelItem
import com.godamy.marvelcompose.ui.screen.common.ArrowBack

@ExperimentalMaterialApi
@Composable
fun DetailAppBar(
    marvelItem: MarvelItem,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = marvelItem.title) },
        navigationIcon = {
            ArrowBack(onBackClick)
        },
        actions = {
            DetailAppBarMenu(marvelItem)
        }
    )
}
