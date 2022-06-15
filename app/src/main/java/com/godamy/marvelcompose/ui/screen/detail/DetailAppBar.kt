package com.godamy.marvelcompose.ui.screen.detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.ui.screen.common.ArrowBack

@ExperimentalMaterialApi
@Composable
fun DetailAppBar(
    comic: Comic,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = comic.title) },
        navigationIcon = {
            ArrowBack(onBackClick)
        },
        actions = {
            DetailAppBarMenu(comic)
        }
    )
}
